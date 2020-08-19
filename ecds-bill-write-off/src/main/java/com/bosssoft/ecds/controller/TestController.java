package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TestController {

    @Autowired(required = false)
    private BillService billService;

    @Autowired(required = false)
    private FinancialWriteOffService financialWriteOffService;

    @GetMapping("/test")
    public String test(){
        return billService.getBillInfoList("1");
    }

    @GetMapping("/detail")
    public WriteOffDetailDTO detail(){
        return financialWriteOffService.getDetail("1");
    }
}
