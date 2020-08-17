package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.service.BillService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BillController {

    @Resource
    BillService billService;

    @RequestMapping("/createBill")
    public int createBill(@RequestBody List<BillDto> list) {
        return billService.createBill(list);
    }

    @RequestMapping("/retrieveBill")
    public List retrieveBill(@RequestParam int number) {
        return billService.retrieveBill(number);
    }
}
