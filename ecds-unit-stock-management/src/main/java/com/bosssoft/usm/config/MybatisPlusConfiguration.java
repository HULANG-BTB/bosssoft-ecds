package com.bosssoft.usm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description MybatisPlus配置类
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisPlusConfiguration {
    /*@Autowired
    HttpServletRequest httpServletRequest;

    *//**
     * 注册字段自动填充
     *
     * @return
     *//*
    @Bean
    public MetaObjectHandler getMetaObjectHandler() {

        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                *//**
                 * String authId = httpServletRequest.getHeader("auth_id");
                 * String authNickname = httpServletRequest.getHeader("auth_nickname");
                 * 生产环境使用上面的方式获取ID和nickname
                 * 测试环境模拟获得ID和nickname使用下面的方式
                 *//*
                Long authId = 1L;
                String authNickname = "test";
                this.setFieldValByName("createTime", new Date(), metaObject);
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("operatorId", authId, metaObject);
                this.setFieldValByName("operator", authNickname, metaObject);

            }

            @Override
            public void updateFill(MetaObject metaObject) {
                *//**
                 * String authId = httpServletRequest.getHeader("auth_id");
                 * String authNickname = httpServletRequest.getHeader("auth_nickname");
                 * 生产环境使用上面的方式获取ID和nickname
                 * 测试环境模拟获得ID和nickname使用下面的方式
                 *//*
                Long authId = 1L;
                String authNickname = "test";
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("operatorId", authId, metaObject);
                this.setFieldValByName("operator", authNickname, metaObject);
            }
        };
    }

    *//**
     * 乐观锁插件
     *
     * @return
     *//*
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    *//**
     * 分页插件
     *
     * @return
     *//*
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
*/}
