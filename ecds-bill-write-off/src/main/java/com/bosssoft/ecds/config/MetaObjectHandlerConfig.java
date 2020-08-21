package com.bosssoft.ecds.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hujierong
 * @date 2020-8-16
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        /**
         * String authId = httpServletRequest.getHeader("auth_id");
         * String authNickname = httpServletRequest.getHeader("auth_nickname");
         * 生产环境使用上面的方式获取ID和nickname
         * 测试环境模拟获得ID和nickname使用下面的方式
         */
        Long authId = 1L;
        String authNickname = "test";
        this.setFieldValByName("fCreateTime", new Date(), metaObject);
        this.setFieldValByName("fUpdateTime", new Date(), metaObject);
        this.setFieldValByName("fOperatorId", authId, metaObject);
        this.setFieldValByName("fOperator", authNickname, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /**
         * String authId = httpServletRequest.getHeader("auth_id");
         * String authNickname = httpServletRequest.getHeader("auth_nickname");
         * 生产环境使用上面的方式获取ID和nickname
         * 测试环境模拟获得ID和nickname使用下面的方式
         */
        Long authId = 1L;
        String authNickname = "test";
        this.setFieldValByName("fUpdateTime", new Date(), metaObject);
        this.setFieldValByName("fOperatorId", authId, metaObject);
        this.setFieldValByName("fOperator", authNickname, metaObject);
    }
}
