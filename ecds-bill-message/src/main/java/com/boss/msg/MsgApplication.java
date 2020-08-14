package com.boss.msg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhangxiaohui
 * 消息推送模块，提供短信、邮件推送功能
 * todo 票据查验，图片识别，百度AI,MQ接收
 * vo、queryVo能不能进service层？
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan("com.boss.msg.mapper")
public class MsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgApplication.class, args);
    }

}
