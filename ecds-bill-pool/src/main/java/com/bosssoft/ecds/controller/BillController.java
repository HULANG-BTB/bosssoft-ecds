package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.ExportBillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;
import com.bosssoft.ecds.service.BillService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Api
@RestController
public class BillController {

    @Resource
    BillService billService;

    @RequestMapping(value = "/createBill", method = RequestMethod.POST)
    public int createBill(@RequestBody @Valid BillDto billDto) {
        return billService.createBill(billDto);
    }

    @RequestMapping(value = "/retrieveBill", method = RequestMethod.POST)
    public ExportBillDto retrieveBill(@RequestBody @Valid RetrieveBillDto retrieveBillDto) {
        return billService.retrieveBill(retrieveBillDto);
    }
}