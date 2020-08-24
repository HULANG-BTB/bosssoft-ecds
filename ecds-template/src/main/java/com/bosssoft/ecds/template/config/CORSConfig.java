package com.bosssoft.ecds.template.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域设置，使用反向代理时请关闭此配置
 */
//@Configuration
public class CORSConfig implements WebMvcConfigurer {
    /**
     * 允许所有源的部分方法跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "HEAD",
                        "OPTIONS"
                )
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
