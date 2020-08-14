package com.bosssoft.ecds.handler.quartz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MonitorStartRunner implements CommandLineRunner {

    @Resource
    private ScheduleJob scheduleJob;

    @Override
    public void run(String... args) throws Exception {
        scheduleJob.scheduleJobs();
    }
}
