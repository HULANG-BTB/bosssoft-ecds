package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RabbitListener(queues = "ExhaustQueueFirst")
public class FanoutBillExhaustReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FanoutBillExhaustReceiver.class);

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @RabbitHandler
    public void handle(String billTypeCode) {
        fanoutRabbitUtils.sendBillDelayMessage(billTypeCode);
    }
}
