package com.bosssoft.ecds;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = {ArchiveApplication.class})
@Slf4j
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        String key = "HelloWorld";
        String res = (String) redisTemplate.opsForValue().get(key);
        System.out.println(res);
    }
}
