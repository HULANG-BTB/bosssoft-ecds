package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FanoutBillWarnReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FanoutBillWarnReceiver.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @RabbitListener(queues = "warnQueueFirst")
    @RabbitHandler
    public void handle(String billTypeCode) {
        fanoutRabbitUtils.sendBillDelayMessage(billTypeCode);
        logger.info(billTypeCode + " 放票请求已发送" + dateFormat.format(new Date()));
    }
}
