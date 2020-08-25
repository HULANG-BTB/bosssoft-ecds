package com.bosssoft.ecds.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@MapperScan("com.bosssoft.ecds.dao")
@EnableTransactionManagement
@Configuration // 配置类
public class MyBatisPlusConfig {
    // 注册乐观锁插件
    @Autowired
    HttpServletRequest httpServletRequest;

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    @Bean
    public MetaObjectHandler getMetaObjectHandler() {

        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                /**
                 * String authId = httpServletRequest.getHeader("auth_id");
                 * String authNickname = httpServletRequest.getHeader("auth_nickname");
                 * 生产环境使用上面的方式获取ID和nickname
                 * 测试环境模拟获得ID和nickname使用下面的方式
                 */
                String authNickname = Base64.getDecoder().decode(httpServletRequest.getHeader("auth_nickname")).toString();
                this.setFieldValByName("fAuthor", authNickname, metaObject);

            }

            @Override
            public void updateFill(MetaObject metaObject) {
                /**
                 * String authId = httpServletRequest.getHeader("auth_id");
                 * String authNickname = httpServletRequest.getHeader("auth_nickname");
                 * 生产环境使用上面的方式获取ID和nickname
                 * 测试环境模拟获得ID和nickname使用下面的方式
                 */
                String authNickname = new String(Base64.getDecoder().decode(httpServletRequest.getHeader("auth_nickname")));
                this.setFieldValByName("fAuthor", authNickname, metaObject);
            }
        };
    }
}
