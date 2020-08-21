package com.bosssoft.ecds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@MapperScan("com.bosssoft.ecds.dao")
public class BillAccountingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillAccountingApplication.class, args);
    }
}
