package com.bosssoft.ecds.handler.quartz;

import com.bosssoft.ecds.handler.quartz.job.SourceMonitorJob;
import com.bosssoft.ecds.handler.quartz.job.SourceSetMonitorJob;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ScheduleJob {

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleSourceMonitorJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SourceMonitorJob.class)
                .withIdentity("SourceMonitorJob", "SourceMonitorJob").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/8 * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("SourceMonitorJob", "SourceMonitorJob")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void scheduleSourceSetMonitorJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SourceSetMonitorJob.class)
                .withIdentity("SourceSetMonitorJob", "SourceSetMonitorJob").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/30 * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("SourceSetMonitorJob", "SourceSetMonitorJob")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleSourceMonitorJob(scheduler);
        scheduleSourceSetMonitorJob(scheduler);
    }
}
