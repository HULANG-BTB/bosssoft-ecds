package com.bosssoft.ecds;

//import com.alibaba.nacos.api.annotation.NacosProperties;
//import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import lombok.extern.slf4j.Slf4j;
//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@Slf4j
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableHystrix
//@RefreshScope
@MapperScan("com.bosssoft.ecds.mapper")
public class FSMApplication {

	public static void main(String[] args) {
		SpringApplication.run(FSMApplication.class, args);
		log.info("start--------------------------------------");
	}

}
