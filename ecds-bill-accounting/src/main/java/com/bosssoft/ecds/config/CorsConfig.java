package com.bosssoft.ecds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName CrossConfig
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 11:00
 * @Version 1.0
 */

@Configuration
public class CorsConfig {
    // 设置允许跨域的源
    private static String[] originsVal = new String[]{
            "127.0.0.1:8001",
            "localhost:8001",
            "localhost:9527",
            "172.21.0.11:8001",
            "140.143.14.147:8001",
            ""
    };

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        this.addAllowedOrigins(corsConfiguration);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    /**
     * 配置允许跨域来源
     * @param corsConfiguration
     */
    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
        for (String origin : originsVal) {
            corsConfiguration.addAllowedOrigin("http://" + origin);
            corsConfiguration.addAllowedOrigin("https://" + origin);
        }
        corsConfiguration.addAllowedOrigin("*");
    }
}
