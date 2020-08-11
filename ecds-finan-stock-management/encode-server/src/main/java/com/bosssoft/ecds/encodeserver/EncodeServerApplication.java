package com.bosssoft.ecds.encodeserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 黄杰峰
 */
@SpringBootApplication
@MapperScan("com.bosssoft.ecds.encodeserver.mapper")
public class EncodeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncodeServerApplication.class, args);
    }

}
