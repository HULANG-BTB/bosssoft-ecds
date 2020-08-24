package com.bosssoft.ecds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bosssoft.ecds.dao")
public class ProviderApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
