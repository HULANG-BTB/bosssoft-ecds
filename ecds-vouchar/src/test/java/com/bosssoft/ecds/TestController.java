package com.bosssoft.ecds;

import com.bosssoft.ecds.controller.ArchiveOverViewController;
import com.bosssoft.ecds.controller.BillApplyArchiveController;
import com.bosssoft.ecds.controller.BillAvailableArchiveController;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.entity.query.CommonQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ArchiveApplication.class})
@Slf4j
public class TestController {

    @Autowired
    ArchiveOverViewController archiveOverViewController;
    @Autowired
    BillApplyArchiveController billApplyArchiveController;
    @Autowired
    BillAvailableArchiveController billAvailableArchiveController;

    @Test
    public void testArchiveOverView() {
        ArchiveOverViewQuery query = new ArchiveOverViewQuery();
        query.setAgenName("");
        query.setPage(2);
        query.setLimit(2);
        String s = archiveOverViewController.queryArchiveAllInfo(query);
        log.info(s);
    }

    @Test
    public void testBillApply() {
        CommonQuery query = new CommonQuery();
        query.setAgenCode("112233");
        query.setPage(2);
        query.setLimit(2);
        String s = billApplyArchiveController.info(query);
        log.info(s);
    }

    @Test
    public void testBillAvailable() {
        CommonQuery query = new CommonQuery();
        query.setAgenCode("22222222222");
        query.setPage(2);
        query.setLimit(3);
        String s = billAvailableArchiveController.info(query);
        log.info(s);
    }
}
