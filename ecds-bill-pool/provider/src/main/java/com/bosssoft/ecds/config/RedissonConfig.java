package com.bosssoft.ecds.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Configuration
public class RedissonConfig {
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson/redissonConfig.yml").getInputStream()));
    }
}
