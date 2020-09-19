package com.bosssoft.ecds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author syf
 * @Date 2020/8/11 10:12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class,args);
    }
}
