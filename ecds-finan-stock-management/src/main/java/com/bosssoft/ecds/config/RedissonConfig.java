package com.bosssoft.ecds.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 黄杰峰
 * @Date 2020/8/8 0008 20:15
 * @Description 注入RedissonClient配置类
 *  使用redisson，不需要创建连接池，会自动维护，只需将redisson实例创建为单例模式（完全线程安全）并使用。
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 使用集群模式
        config.useClusterServers()
                // 扫描间隔时间，单位毫秒
                .setScanInterval(2000)
                .addNodeAddress("redis://39.108.14.141:7000",
                        "redis://39.108.14.141:7001",
                        "redis://39.108.14.141:7002",
                        "redis://39.108.14.141:7003",
                        "redis://39.108.14.141:7004",
                        "redis://39.108.14.141:7005").setPassword("123456");
        return Redisson.create(config);
    }

}
