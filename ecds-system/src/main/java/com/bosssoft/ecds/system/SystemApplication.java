package com.bosssoft.ecds.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangxiaohui
 * 消息推送模块，提供短信、邮件推送功能，票据查验功能
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableAsync
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
