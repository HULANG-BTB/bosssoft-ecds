package com.bosssoft.ecds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/13
 * @Content:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .enable(b)
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bosssoft.ecds.controller"))
                // 配置如何通过path过滤,即这里只扫描请求以/kuang开头的接口
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置文档相关信息
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("李大山", "http://xxx.xxx.com", "tomato@qq.com");
        return new ApiInfo(
                // 标题
                "unit_sign",
                // 描述
                "单位签名微服务接口展示",
                // 版本
                "v1.0",
                // 组织链接
                "http://terms.service.url/组织链接",
                // 联系人信息
                contact,
                // 许可
                "Authorization By : Central Network Supervision Department of the Big Tomato Empire ",
                // 许可连接
                "http://www.gov.cn/"
        );
    }
}
