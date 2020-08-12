package com.bosssoft.ecds.handler.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "warnQueueFirst")
public class FanoutBillWarnReceiver {

    @RabbitHandler
    public void handle(String message) {
        System.out.println(message);
    }
}
