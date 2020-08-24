package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.ExportBillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;
import com.bosssoft.ecds.service.BillService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);
    @Resource
    BillService billService;

    @RequestMapping(value = "/createBill", method = RequestMethod.POST)
    public int createBill(@RequestBody @Valid BillDto billDto) {
        return billService.createBill(billDto);
    }

    @RequestMapping(value = "/retrieveBill", method = RequestMethod.POST)
    public ExportBillDto retrieveBill(@RequestBody @Valid RetrieveBillDto retrieveBillDto) {
        logger.info("over");
        return billService.retrieveBill(retrieveBillDto);
    }
}
