package com.bosssoft.ecds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName GatewayApplication
 * @Author AloneH
 * @Date 2020/8/9 10:28
 * @Description TODO
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.bosssoft.ecds.security.dao"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
