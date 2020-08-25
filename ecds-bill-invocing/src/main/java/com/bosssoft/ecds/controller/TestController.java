package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.AgenInfoDTO;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.BillService;
import com.bosssoft.ecds.service.client.MessageService;
import com.bosssoft.ecds.service.client.TestService;
import com.bosssoft.ecds.service.client.UnitManagerService;
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

    @Autowired
    UnitManagerService unitManagerService;

    @GetMapping("/test")
    @ResponseBody
    public QueryResponseResult test() throws ExecutionException, InterruptedException {
        QueryResponseResult queryResponseResult = billService.getBill();
        return queryResponseResult;
    }

    @GetMapping("/file")
    @ResponseBody
    public QueryResponseResult file() throws ExecutionException, InterruptedException {
        QueryResponseResult queryResponseResult = billService.getBill();
        return queryResponseResult;
    }

    @GetMapping("/unit")
    @ResponseBody
    public QueryResponseResult getunit() throws ExecutionException, InterruptedException {
        String ageName = "博思软件股份有限公司";
        AgenInfoDTO agenInfoDTO = unitManagerService.getDetailByUnitName(ageName);
        return new QueryResponseResult(CommonCode.SUCCESS, agenInfoDTO);
    }
}
