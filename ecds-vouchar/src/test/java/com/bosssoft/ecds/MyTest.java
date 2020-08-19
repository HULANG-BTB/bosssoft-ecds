package com.bosssoft.ecds;

import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.service.*;
import com.bosssoft.ecds.service.impl.BillCheckArchiveServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ArchiveApplication.class})
@Slf4j
public class MyTest {

    @Autowired
    BillAvailableArchiveService service;
    @Autowired
    ItemArchiveService itemArchiveService;
    @Autowired
    BillCheckArchiveServiceImpl billCheckArchiveService;
    @Autowired
    BillApplyArchiveService billApplyArchiveService;
    @Autowired
    BillWarnArchiveService billWarnArchiveService;
    @Autowired
    BillPayArchiveService billPayArchiveService;
    @Autowired
    ArchiveOverViewService archiveOverViewService;
    @Autowired
    ArchiveCycleService archiveCycleService;

    @Test
    public void testBillAvailable() {
        service.finaBillApplyArchive();
    }

    @Test
    public void testItemAvailable() {
        itemArchiveService.finaItemAvailableArchive();
    }

    @Test
    public void testBillCheckArchiveService() {
        billCheckArchiveService.finaBillCheckArchive();
    }

    @Test
    public void testBillApplyService() {
        billApplyArchiveService.finaBillApplyArchive();
    }

    @Test
    public void testBillWarnService() {
        billWarnArchiveService.finaBillWarnArchive();
    }

    @Test
    public void testBillPayService() {
        billPayArchiveService.finaBillPayArchive();
    }

    @Test
    public void testQuery() {
        ArchiveOverViewQuery query = new ArchiveOverViewQuery();
        query.setAgenName("软件");
        log.info("" + archiveOverViewService.queryOverViewArchiveInfo(query));
    }

    @Test
    public void testArchiveCycleService() {
        archiveCycleService.exec();
    }
}
