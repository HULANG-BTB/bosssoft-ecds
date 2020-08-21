package com.bosssoft.ecds.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Component
public class FanoutRabbitUtils {

    private static final Logger logger = LoggerFactory.getLogger(FanoutRabbitUtils.class);

    @Resource
    private AmqpTemplate rabbitTemplate;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public void sendBillWarn(String billTypeCode) {
        String message = billTypeCode + " 低于阈值";
        logger.warn(message + " " + dateFormat.format(new Date()));
        rabbitTemplate.convertAndSend("fanoutWarnExchange", null, billTypeCode);
    }

    public void sendBillExhaust(String billTypeCode) {
        String message = billTypeCode + " 已耗尽";
        logger.warn(message + " " + dateFormat.format(new Date()));
        rabbitTemplate.convertAndSend("fanoutExhaustExchange", null, billTypeCode);
    }

    public void sendBillDelayMessage(String billTypeCode) {
        rabbitTemplate.convertAndSend("fanoutErrorExchange", null, billTypeCode, message -> {
            long expire = 10 * 60 * 1000;
            message.getMessageProperties().setExpiration(String.valueOf(expire));
            return message;
        });
    }
}
