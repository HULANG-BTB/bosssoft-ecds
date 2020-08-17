package com.bosssoft.ecds.msg.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangxiaohui
 * @create 2020/7/29 13:59
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置docket以配置Swagger具体参数
     * 相关注解的作用：
     * ## @Api(tags = "模块说明")
     * ## @ApiOperation("接口方法说明")
     * ## @ApiModel("POJO说明")
     * ## @ApiModelProperty(value = "属性说明",hidden = true)	作用在类方法和属性上，hidden设置为true可以隐藏该属性
     * ## @ApiParam("xxx参数说明")
     */
    @Bean
    public Docket docket(Environment environment) {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启swagger,获取当前环境的环境，来开关swagger
                .enable(environment.acceptsProfiles(Profiles.of("dev","test")))
                // 添加组名，配置多个bean对应个人的接口
                .groupName("ecds-bill-message")
                // 配置扫描的包，方法，注解的规则。例如：可以通过自定义注解，实现不扫描该注解下的方法。
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bosssoft.ecds.msg.controller"))
                // 配置如何通过path过滤
                //.paths()
                .build();

    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("票据消息推送及查验模块")
                .description("开放短信、邮件消息推送，及其发件记录的查询。票据真伪查验")
                .version("v1.0").build();
    }
}
