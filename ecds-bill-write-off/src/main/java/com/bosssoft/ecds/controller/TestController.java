package com.bosssoft.ecds.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.entity.dto.WriteOffDto;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TestController {

    @Autowired(required = false)
    private BillService billService;

    @GetMapping("/test")
    public String test(){
        return billService.getBillInfoList("1");
    }

    @GetMapping("/test1")
    public Object test1(String start, String end) {
        ResponseUtils.ResponseBody responseBody = JSONUtil.toBean(billService.getWriteOffInfo(start, end), ResponseUtils.ResponseBody.class);
        List<WriteOffDto> list = Convert.toList(WriteOffDto.class, responseBody.getData());
        return list;
    }
}
