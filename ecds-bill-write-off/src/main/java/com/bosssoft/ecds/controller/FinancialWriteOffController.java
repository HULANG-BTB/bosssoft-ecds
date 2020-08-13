package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 财务段的核销服务
 */
@RestController
public class FinancialWriteOffController {

    @Autowired
    private FinancialWriteOffService financialWriteOffService;

    @ResponseBody
    @GetMapping("/get")
    public String get(){
        return "";
    }
}
