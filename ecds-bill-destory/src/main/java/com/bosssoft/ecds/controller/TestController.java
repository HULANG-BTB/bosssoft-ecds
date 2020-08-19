package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.feign.Test;
import com.bosssoft.ecds.service.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: qiuheng
 * @create: 2020-08-17 23:52
 **/
@RestController
public class TestController {
    @Autowired
    private Test test;

    @Autowired
    private TestFeign testFeign;

    @RequestMapping(value = "test")
    public String test(String billId, String checkCode){
        String msg = test.test(billId,checkCode);
        return msg;
    }

    @GetMapping(value = "testFeign")
    public String testFeign(Integer id){
        return testFeign.test(id);
    }
}
