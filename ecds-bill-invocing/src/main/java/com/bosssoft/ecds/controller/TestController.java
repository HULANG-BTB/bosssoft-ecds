package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.BillService;
import com.bosssoft.ecds.service.client.MessageService;
import com.bosssoft.ecds.service.client.TestService;
import com.bosssoft.ecds.util.AliyunOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequestMapping(path = "/test")
public class TestController {


    @Autowired(required = false)
    private MessageService testService;

    @Autowired
    private BillService billService;


    @Autowired
    AliyunOSSUtil ossUtil;

    @GetMapping("/test")
    @ResponseBody
    public QueryResponseResult test() throws ExecutionException, InterruptedException {
        QueryResponseResult queryResponseResult = billService.getBill();
        return queryResponseResult;
    }
}
