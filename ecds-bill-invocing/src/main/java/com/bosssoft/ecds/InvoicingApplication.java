package com.bosssoft.ecds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.bosssoft.ecds.dao"})
public class InvoicingApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoicingApplication.class, args);
    }
}
