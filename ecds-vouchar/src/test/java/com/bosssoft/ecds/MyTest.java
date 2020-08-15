package com.bosssoft.ecds;

import com.bosssoft.ecds.service.BillApplyArchiveService;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import com.bosssoft.ecds.service.BillWarnArchiveService;
import com.bosssoft.ecds.service.ItemArchiveService;
import com.bosssoft.ecds.service.impl.BillCheckArchiveServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ArchiveApplication.class})
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
}
