package com.bosssoft.ecds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @ClassName GatewayApplication
 * @Author AloneH
 * @Date 2020/8/9 10:28
 * @Description
 *      网关主启动类
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.bosssoft.ecds.security.dao"})
@EnableDiscoveryClient
@RefreshScope
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
