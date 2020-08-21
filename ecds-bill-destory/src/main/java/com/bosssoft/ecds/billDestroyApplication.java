package com.bosssoft.ecds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: qiuheng
 * @create: 2020-08-11 16:47
 **/
@SpringBootApplication
@MapperScan("com.bosssoft.ecds.dao")
public class billDestroyApplication {
    public static void main(String[] args) {
        SpringApplication.run(billDestroyApplication.class,args);
    }
}
