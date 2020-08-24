package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    RedisTemplate redisTemplate;

    @Resource
    BillService billService;

    @Resource
    RestTemplate restTemplate;

    @RabbitListener(queues = "warnQueueFirst")
    @RabbitHandler
    public void handle(String billTypeCode) throws JsonProcessingException {
        Integer number = (Integer) redisTemplate.opsForHash().get(billTypeCode, "pushNumber");
        RequestBillDto requestBillDto = new RequestBillDto(billTypeCode, number, "source", "source");

        InsertBillDto insertBillDto = new InsertBillDto();
        List<RequestBillDto> list = new ArrayList<>();
        list.add(requestBillDto);

        BillRefineDto refineDto= restTemplate.postForObject("http://finan-stock-management/finan-bill/outBills",
                list, BillRefineDto.class);

        insertBillDto = refineDto.getData().get(0);

        BillDto billDto = new BillDto();
        billDto.setBillTypeCode(insertBillDto.getBillPrecode());
        billDto.setBillCodeBegin(Long.parseLong(insertBillDto.getBillNo1()));
        billDto.setBillCodeEnd(Long.parseLong(insertBillDto.getBillNo2()));
        billDto.init();

        billService.createBill(billDto);

    }
}
