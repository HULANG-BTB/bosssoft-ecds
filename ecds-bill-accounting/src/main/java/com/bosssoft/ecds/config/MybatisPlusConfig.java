package com.bosssoft.ecds.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * <p>
 *  mybatis-plus全局配置
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */

@EnableTransactionManagement
@Configuration
@MapperScan("com.bosssoft.ecds.dao")
public class MybatisPlusConfig {

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 注册字段自动填充
     *
     * @return
     */
    @Bean
    public MetaObjectHandler getMetaObjectHandler() {

        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                String authId = httpServletRequest.getHeader("auth_id");
                String authNickname = new String(Base64.getDecoder().decode(httpServletRequest.getHeader("auth_nickname")));
                this.setFieldValByName("createTime", new Date(), metaObject);
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("operatorId", authId, metaObject);
                this.setFieldValByName("operator", authNickname, metaObject);

            }

            @Override
            public void updateFill(MetaObject metaObject) {
                String authId = httpServletRequest.getHeader("auth_id");
                String authNickname = httpServletRequest.getHeader("auth_nickname");
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("operatorId", authId, metaObject);
                this.setFieldValByName("operator", authNickname, metaObject);
            }
        };
    }

    /**
     * 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
