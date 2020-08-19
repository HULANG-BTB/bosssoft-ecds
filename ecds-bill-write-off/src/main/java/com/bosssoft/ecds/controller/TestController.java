package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TestController {

    @Autowired(required = false)
    private BillService billService;

    @GetMapping("/test")
    public String test(){
        return billService.getBillInfoList("1");
    }
}
