package com.bosssoft.ecds.handler.rabbitmq;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.dao.MailDao;
import com.bosssoft.ecds.entity.dto.ErrorMailDto;
import com.bosssoft.ecds.entity.po.MailPo;
import com.bosssoft.ecds.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Resource
    MailDao mailDao;

    @Resource
    MailService mailService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @RabbitListener(queues = "deadLetterQueue")
    @RabbitHandler
    public void handle(String billTypeCode) {
        if (!redisTemplate.hasKey(billTypeCode)) {
            logger.error(billTypeCode + "不存在");
            return;
        }
        int threshold = (int) redisTemplate.opsForHash().get(billTypeCode, "threshold");
        String table = (String) redisTemplate.opsForHash().get(billTypeCode, "table");
        int remainderBill = billDao.retrieveNumber(table);
        if (remainderBill <= threshold) {
            logger.error(billTypeCode + " 放票请求系统未及时相应" + dateFormat.format(new Date()));
            List<MailPo> mailPoList = mailDao.retrieveMailList();
            List<ErrorMailDto> errorMailDtoList = new ArrayList<>();

            for (int i = 0; i < mailPoList.size(); i++) {
                ErrorMailDto errorMailDto = new ErrorMailDto(mailPoList.get(i).getEmail(), "自动放票系统发生错误",
                        billTypeCode + "未在规定时间内放票");
                errorMailDtoList.add(errorMailDto);
            }
            mailService.sendTemplateMail(errorMailDtoList);
        }
    }
}
