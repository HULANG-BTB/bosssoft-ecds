package com.bosssoft.ecds.handler.quartz.job;

import com.bosssoft.ecds.utils.UpdateSourceMessageUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class SourceSetMonitorJob implements Job {

    @Resource
    private UpdateSourceMessageUtils utils;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        utils.update();
    }
}
