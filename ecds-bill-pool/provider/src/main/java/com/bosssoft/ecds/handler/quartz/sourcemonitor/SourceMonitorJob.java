package com.bosssoft.ecds.handler.quartz.sourcemonitor;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class SourceMonitorJob implements Job {

    private static final Integer THRESHOLD = 3000;

    @Resource
    BillDao billDao;

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int remainderBill = billDao.retrieveNumber();
        if (remainderBill <= THRESHOLD) {
            fanoutRabbitUtils.sendBillWarn();
        }
    }
}
