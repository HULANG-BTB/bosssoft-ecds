package com.bosssoft.ecds.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("wp")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bosssoft.ecds.pay"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("王鹏","","");
        ApiInfo apiInfo = new ApiInfo("王鹏的文档资料", "缴费管理", "v1.0", "",
                contact, "", "",new ArrayList<>());
        return apiInfo;
    }
}
