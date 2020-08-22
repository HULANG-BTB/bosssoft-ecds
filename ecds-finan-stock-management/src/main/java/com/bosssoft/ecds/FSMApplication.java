package com.bosssoft.ecds;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
// @RefreshScope
@MapperScan("com.bosssoft.ecds.mapper")
@MapperScan("com.bosssoft.ecds.dao")
@EnableFeignClients
public class FSMApplication {

	public static void main(String[] args) {
		SpringApplication.run(FSMApplication.class, args);
		log.info("start--------------------------------------");
	}

}
