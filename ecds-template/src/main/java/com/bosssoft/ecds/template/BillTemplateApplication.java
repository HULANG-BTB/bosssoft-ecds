package com.bosssoft.ecds.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bosssoft.ecds.template.mapper")
public class BillTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillTemplateApplication.class, args);
    }

}
