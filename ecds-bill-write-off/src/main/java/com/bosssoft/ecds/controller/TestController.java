package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "SayController|一个用来测试swagger注解的控制器")
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
    @ApiOperation(value = "测试", notes = "获取详细信息")
    public QueryResponseResult detail(){
        WriteOffDetailDTO writeOffDetailDTO = financialWriteOffService.getDetail("1");
        return new QueryResponseResult(CommonCode.SUCCESS, writeOffDetailDTO);
    }
}
