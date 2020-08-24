package com.bosssoft.ecds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujierong
 * @date 2020-8-24
 */
@RestController
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/test")
    public Object test() {
        redisTemplate.opsForValue().set("test", "test");
        return redisTemplate.opsForValue().get("test");
    }
}
