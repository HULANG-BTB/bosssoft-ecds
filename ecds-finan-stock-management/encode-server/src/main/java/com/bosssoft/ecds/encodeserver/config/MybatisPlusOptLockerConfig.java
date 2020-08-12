package com.bosssoft.ecds.encodeserver.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 黄杰峰
 * @Date 2020/8/6 0006 17:29
 * @Description 乐观锁插件配置
 */
@Configuration
public class MybatisPlusOptLockerConfig {
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
