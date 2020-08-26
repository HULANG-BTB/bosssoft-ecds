package com.bosssoft.ecds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author syf
 * @Date 2020/8/10 16:04
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *  配置文档信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "财政报表模块", // 标题
                "财政报表模块接口文档", // 描述
                "v1.0", // 版本
                null, // 组织链接
                null, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bosssoft.ecds.controller"))
                .build();
    }
}
