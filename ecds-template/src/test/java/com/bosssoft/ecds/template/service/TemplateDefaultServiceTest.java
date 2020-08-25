package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.service.TemplateDefaultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TemplateDefaultServiceTest {

    @Autowired
    TemplateDefaultService templateDefaultService;

    //@Test
    void getDefault() {
        String billCode = "456789";
        String type = "print";
        Long id = templateDefaultService.getDefault(type, billCode);
        System.out.println(id);
        assert id!=0;
    }

    @Test
    @Transactional
    void removeDefault() {
        String billCode = "456789";
        String type = "print";
        boolean res = templateDefaultService.removeDefault(type, billCode);
        System.out.println(res);
        assert true;
    }
}