package com.bosssoft.ecds.handler.quartz.job;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

public class SourceMonitorJob implements Job {

    @Resource
    BillDao billDao;

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<String> list = redisTemplate.opsForList().range("RegionCode", 0, -1);

        for (int i = 0; i < list.size(); i++) {
            int threshold = (int) redisTemplate.opsForHash().get(list.get(i), "threshold");
            String table = (String) redisTemplate.opsForHash().get(list.get(i), "table");
            int remainderBill = billDao.retrieveNumber(table);
            if (remainderBill <= threshold) {
                fanoutRabbitUtils.sendBillWarn();
            }
        }
    }
}
