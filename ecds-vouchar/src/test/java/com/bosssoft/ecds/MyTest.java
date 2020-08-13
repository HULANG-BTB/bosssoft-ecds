package com.bosssoft.ecds;

import com.bosssoft.ecds.service.BillAvailableArchiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ArchiveApplication.class})
public class MyTest {

    @Autowired
    BillAvailableArchiveService service;

    @Test
    public void testBillAvailable() {
        service.finaBillApplyArchive();
    }
}
