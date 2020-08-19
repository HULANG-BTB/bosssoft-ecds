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
 * todo 调用整合，附件，新建邮件发送
 * todo 票据查验，用户用票据号码加校验码查询，fegin调用服务接口查询真伪
 * todo 票据查验记录的增删改查，查验信息展示
 * todo 图片识别，调用百度AI识别票据号码及校验码查询
 * vo、queryVo能不能进service层？
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
