package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.utils.UpdateSourceMessageUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    UpdateSourceMessageUtils utils;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    BillDao billDao;

    @RequestMapping("/test")
    public void test() {
        utils.update();
    }
}
