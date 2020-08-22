package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author liuke
 */
@Service
@Slf4j(topic = "kafka_business_logger")
public class ArchiveCycleServiceImpl implements ArchiveCycleService {

    @Autowired
    BillApplyArchiveService billApplyArchiveService;
    @Autowired
    BillAvailableArchiveService billAvailableArchiveService;
    @Autowired
    ItemArchiveService itemArchiveService;
    @Autowired
    BillCheckArchiveService billCheckArchiveService;
    @Autowired
    BillPayArchiveService billPayArchiveService;
    @Autowired
    BillWarnArchiveService billWarnArchiveService;

    /**
     * 执行定期归档任务  每天 00:00 执行归档操作
     */
    @Async
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void exec() {
        billApplyArchiveService.finaBillApplyArchive();
        itemArchiveService.finaItemAvailableArchive();
        billAvailableArchiveService.finaBillApplyArchive();
        billCheckArchiveService.finaBillCheckArchive();
        billPayArchiveService.finaBillPayArchive();
        billWarnArchiveService.finaBillWarnArchive();
    }
}
