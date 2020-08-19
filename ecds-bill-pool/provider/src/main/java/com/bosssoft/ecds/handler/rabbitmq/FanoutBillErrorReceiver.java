package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.dao.BillDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
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
public class FanoutBillErrorReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FanoutBillErrorReceiver.class);

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    BillDao billDao;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @RabbitListener(queues = "deadLetterQueue")
    @RabbitHandler
    public void handle(String billTypeCode) {
        if (!redisTemplate.hasKey(billTypeCode)){
            logger.error(billTypeCode + "不存在");
            return;
        }
        int threshold = (int) redisTemplate.opsForHash().get(billTypeCode, "threshold");
        String table = (String) redisTemplate.opsForHash().get(billTypeCode, "table");
        int remainderBill = billDao.retrieveNumber(table);
        if (remainderBill <= threshold) {
            logger.error(billTypeCode + " 放票请求系统未及时相应" + dateFormat.format(new Date()));
        }
    }
}
