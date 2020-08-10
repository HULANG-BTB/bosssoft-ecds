package com.bosssoft.usm.controller;

import com.bosssoft.usm.entity.po.Test;
import com.bosssoft.usm.service.impl.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */
@RestController
public class TestController {
    @Resource
    private TestServiceImpl testService;

    @GetMapping("/get")
    public Test get(Integer id){
        return testService.getByPrimaryKey(id);
    }
}
