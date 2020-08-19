package com.bosssoft.ecds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hujierong
 * @date 2020-8-11
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class  WriteOffApplication {
    public static void main(String[] args) {
        SpringApplication.run(WriteOffApplication.class, args);
    }
}
