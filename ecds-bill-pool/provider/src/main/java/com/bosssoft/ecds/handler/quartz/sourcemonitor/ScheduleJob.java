package com.bosssoft.ecds.handler.quartz.sourcemonitor;

import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ScheduleJob {

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleMonitorJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SourceMonitorJob.class)
                .withIdentity("scheduleMonitorJob", "scheduleMonitorJob").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/15 * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("scheduleMonitorJob", "scheduleMonitorJob")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleMonitorJob(scheduler);
    }
}
