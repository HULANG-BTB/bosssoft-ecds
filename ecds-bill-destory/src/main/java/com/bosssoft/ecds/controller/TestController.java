package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.service.feign.Test;
import com.bosssoft.ecds.service.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qiuheng
 * @create: 2020-08-17 23:52
 **/
@RestController
public class TestController {
    @Autowired
    private Test test;

    @Autowired
    private TestFeign testFeign;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(String billId, String checkCode){
        String msg = test.test(billId,checkCode);
        return msg;
    }

    @GetMapping(value = "testFeign")
    public String testFeign(Integer id){
        return testFeign.test(id);
    }

    @PostMapping(value = "/testRedis")
    public Object testRedis(){
        redisTemplate.opsForValue().set("test", "test11");
        System.out.println(redisTemplate.opsForValue().get("test"));
        return redisTemplate.opsForValue().get("test");
    }
}
