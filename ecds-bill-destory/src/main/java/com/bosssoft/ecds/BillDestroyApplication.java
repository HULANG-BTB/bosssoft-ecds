package com.bosssoft.ecds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: qiuheng
 * @create: 2020-08-11 16:47
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.bosssoft.ecds.service.feign")
public class BillDestroyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillDestroyApplication.class,args);
    }
}
