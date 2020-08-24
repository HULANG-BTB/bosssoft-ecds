package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Component
public class FanoutBillWarnReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FanoutBillWarnReceiver.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @Resource
    RestTemplate restTemplate;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    BillService billService;

    @RabbitListener(queues = "warnQueueFirst")
    @RabbitHandler
    public void handle(String billTypeCode) {
        fanoutRabbitUtils.sendBillDelayMessage(billTypeCode);

        int number = (int) redisTemplate.opsForHash().get(billTypeCode, "pushNumber");
        RequestBillDto requestBillDto = new RequestBillDto(billTypeCode, number, "source", "source");

        List<RequestBillDto> list = new ArrayList<>();
        list.add(requestBillDto);

        BillRefineDto refineDto = restTemplate.postForObject("http://finan-stock-management/finan-bill/outBills", list, BillRefineDto.class);

        logger.info(refineDto.toString());
        InsertBillDto insertBillDto = refineDto.getData().get(0);
        BillDto billDto = new BillDto();
        billDto.setBillTypeCode(insertBillDto.getBillPrecode());
        billDto.setBillCodeBegin(Long.parseLong(insertBillDto.getBillNo1()));
        billDto.setBillCodeEnd(Long.parseLong(insertBillDto.getBillNo2()));
        billDto.init();

        billService.createBill(billDto);
    }
}
