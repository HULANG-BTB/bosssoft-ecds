package com.bosssoft.ecds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

/**
 * @ClassName CorsConfig
 * @Author AloneH
 * @Date 2020/8/9 10:50
 * @Description
 *              在前后端分离请求中需要配置跨域支持
 *                  1、创建配置类
 *                  2、配置允许跨域列表
 *                  3、配置允许跨域方法
 *                  4、配置跨域信息请求头
 **/

@Configuration
public class CorsConfig {
    // 设置允许跨域的源
    private static String[] originsVal = new String[]{
            "127.0.0.1:8080",
            "localhost:8080",
            "localhost:9527",
            ""
    };

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        this.addAllowedOrigins(corsConfiguration);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }

    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
        for (String origin : originsVal) {
            corsConfiguration.addAllowedOrigin("http://" + origin);
            corsConfiguration.addAllowedOrigin("https://" + origin);
        }
        corsConfiguration.addAllowedOrigin("*");
    }
}
