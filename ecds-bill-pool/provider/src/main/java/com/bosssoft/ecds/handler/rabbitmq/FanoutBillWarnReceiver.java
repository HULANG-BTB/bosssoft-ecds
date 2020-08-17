package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FanoutBillWarnReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FanoutBillWarnReceiver.class);

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @RabbitListener(queues = "warnQueueFirst")
    @RabbitHandler
    public void handle(String billTypeCode) {
        fanoutRabbitUtils.sendBillDelayMessage(billTypeCode);
        logger.info("放票请求已发送");
    }
}
