package com.bosssoft.usm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张东海
 * @date 2020/7/23
 * @description
 */
@SpringBootApplication
@MapperScan("com.bosssoft.usm.dao")
public class USMMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(USMMainApplication.class,args);
    }
}
