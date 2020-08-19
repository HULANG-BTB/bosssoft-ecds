package com.bosssoft.ecds.handler.quartz;

import com.bosssoft.ecds.utils.UpdateSourceMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
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
public class MonitorStartRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MonitorStartRunner.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Resource
    private ScheduleJob scheduleJob;

    @Resource
    private UpdateSourceMessageUtils updateSourceMessageUtils;

    @Override
    public void run(String... args) throws Exception {
        scheduleJob.scheduleJobs();
        updateSourceMessageUtils.update();
        logger.info("定时任务已经启动!" + dateFormat.format(new Date()));
    }
}
