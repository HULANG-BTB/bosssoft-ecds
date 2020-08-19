package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.MessageService;
import com.bosssoft.ecds.service.client.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping(path = "/test")
public class TestController {


    @Autowired(required = false)
    private MessageService testService;

    @GetMapping("/test")
    @ResponseBody
    public String test(@RequestParam("value") String value) throws ExecutionException, InterruptedException {
        testService.test(value);
        return "";
    }
}
