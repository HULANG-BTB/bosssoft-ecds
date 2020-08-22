package com.bosssoft.ecds.msg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author zhangxiaohui
 * 消息推送模块，提供短信、邮件推送功能，票据查验功能
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@EnableAsync
@MapperScan("com.bosssoft.ecds.msg.mapper")
public class MsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgApplication.class, args);
    }


}
