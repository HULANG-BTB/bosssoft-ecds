package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    @Autowired
    private UneCbillService uneCbillService;

    @RequestMapping("/getList")
    public String getUneCBill() {
        int i = 1;
        UneCbill uneCbill = uneCbillService.getUneCBillById(1);
        return uneCbill.toString();
    }
}
