package com.bosssoft.ecds.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                //是否禁用
                .enable(true)
                //设置分组
                .groupName("归档微服务")
                .select()
                //配置扫描组件  basePackage()更具包进行扫描
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 指明 groupName
     */

    /**
     *  设置api的详细信息
     * @return
     */
    private ApiInfo getApiInfo(){
        /**
         * 作者信息
         */
        Contact contact = new Contact("lk","https://github.com/zhuyeqi","liuke311@126.com");
        return new ApiInfo(
                "归档微服务接口文档",
                "归档微服务",
                "1.0",
                "http://www.bosssoft.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0");
    }
}
