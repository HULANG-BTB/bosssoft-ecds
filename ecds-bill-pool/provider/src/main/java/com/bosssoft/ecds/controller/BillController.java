package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;
import com.bosssoft.ecds.service.BillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Resource
    BillService billService;

    @PostMapping("/createBill")
    public int createBill(@RequestBody BillDto billDto) {
        return billService.createBill(billDto);
    }

    @PostMapping("/retrieveBill")
    public List retrieveBill(@RequestBody RetrieveBillDto retrieveBillDto) {
        return billService.retrieveBill(retrieveBillDto);
    }
}
