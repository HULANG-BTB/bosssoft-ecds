package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.service.ArchiveCycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArchiveCycleServiceImpl implements ArchiveCycleService {
    /**
     * 执行定期归档任务  每天 00:00 执行归档操作
     */
    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void exec() {
        log.info("归档任务开始执行");

        log.info("归档任务执行完成");
    }
}
