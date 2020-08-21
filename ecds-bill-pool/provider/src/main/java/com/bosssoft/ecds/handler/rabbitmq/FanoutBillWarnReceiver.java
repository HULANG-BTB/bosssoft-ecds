package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.entity.dto.*;
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

    @RabbitListener(queues = "warnQueueFirst")
    @RabbitHandler
    public void handle(String billTypeCode) {
        RestTemplate restTemplate = new RestTemplate();
        Integer number = (Integer) redisTemplate.opsForHash().get(billTypeCode, "pushNumber");
        RequestBillDto billDto = new RequestBillDto(billTypeCode, number, "source", "source");
        ExportBillDto exportBillDto;
        RetrieveBillDto retrieveBillDto = new RetrieveBillDto();
        retrieveBillDto.setBillTypeCode("88888888");
        retrieveBillDto.setNumber(10);
        InsertBillDto insertBillDto = new InsertBillDto();
        exportBillDto = restTemplate.postForObject("http://127.0.0.1:8083/retrieveBill", retrieveBillDto, ExportBillDto.class);
        logger.info(exportBillDto.toString());
        BillDto dto = new BillDto();
        dto.setBillTypeCode(exportBillDto.getRegionCode());
        dto.setBillCodeBegin(exportBillDto.getBillCodeBegin());
        dto.setBillCodeEnd(exportBillDto.getBillCodeEnd());
        logger.info(dto.toString());
        restTemplate.postForObject("http://127.0.0.1:8083/createBill", dto, int.class);
        fanoutRabbitUtils.sendBillDelayMessage(billTypeCode);
    }
}
