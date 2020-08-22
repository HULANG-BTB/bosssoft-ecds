package com.bosssoft.ecds.msg.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author zhangxiaohui
 */
@Configuration
public class MybatisPlusConfig {
    @Resource
    private HttpServletRequest httpServletRequest;

    /**
     * 注册字段自动填充
     */
    @Bean
    public MetaObjectHandler getMetaObjectHandler() {

        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                /*
                 *
                 *
                 * 生产环境使用上面的方式获取ID和nickname
                 * 测试环境模拟获得ID和nickname使用下面的方式
                 */
//                int authId = 1;
//                String authNickname;
//                String authIdStr = httpServletRequest.getHeader("auth_id");
//                authNickname = httpServletRequest.getHeader("auth_nickname");
//                if (StringUtils.isNotBlank(authIdStr)) {
//                    authId = Integer.parseInt(authIdStr);
//                }
//                if (StringUtils.isBlank(authNickname)) {
//                    // authNickname = httpServletRequest.getRemoteAddr()
//                    authNickname = "test";
//                }
                int authId = 1;
                String authNickname = "test";

                this.setFieldValByName("createTime", new Date(), metaObject);
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("operatorId", authId, metaObject);
                this.setFieldValByName("operator", authNickname, metaObject);
                this.setFieldValByName("version", 0, metaObject);
                this.setFieldValByName("logicDelete", false, metaObject);

            }

            @Override
            public void updateFill(MetaObject metaObject) {
                /*
                 * String authId = httpServletRequest.getHeader("auth_id")
                 * String authNickname = httpServletRequest.getHeader("auth_nickname")
                 * 生产环境使用上面的方式获取ID和nickname
                 * 测试环境模拟获得ID和nickname使用下面的方式
                 */
                Integer authId = 1;
                String authNickname = "test";
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("operatorId", authId, metaObject);
                this.setFieldValByName("operator", authNickname, metaObject);
            }
        };
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}