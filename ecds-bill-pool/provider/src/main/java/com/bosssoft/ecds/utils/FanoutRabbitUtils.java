package com.bosssoft.ecds.utils;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FanoutRabbitUtils {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void sendBillWarn() {
        Integer integer = -1;
        String message = "warn";
        rabbitTemplate.convertAndSend("fanoutWarnExchange", null, message);
    }

    public void sendBillExhaust() {
        Integer integer = -2;
        String message = "exhaust";
        rabbitTemplate.convertAndSend("fanoutExhaustExchange", null, message);
    }
}
