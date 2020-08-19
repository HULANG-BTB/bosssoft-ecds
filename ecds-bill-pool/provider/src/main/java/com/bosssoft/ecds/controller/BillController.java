package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;
import com.bosssoft.ecds.service.BillService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@RestController
@RequestMapping("/bill")
@CrossOrigin
public class BillController {

    @Resource
    BillService billService;

    @RequestMapping("/createBill")
    public int createBill(@RequestBody @Valid BillDto billDto) {
        return billService.createBill(billDto);
    }

    @RequestMapping("/retrieveBill")
    public List retrieveBill(@RequestBody @Valid RetrieveBillDto retrieveBillDto) {
        return billService.retrieveBill(retrieveBillDto);
    }
}
