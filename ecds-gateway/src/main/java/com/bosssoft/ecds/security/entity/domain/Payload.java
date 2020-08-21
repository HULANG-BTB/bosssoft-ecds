package com.bosssoft.ecds.security.entity.domain;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Payload
 * @Author AloneH
 * @Date 2020/8/5 20:53
 * @Description
 *      JWT Payload 实体类
 **/
@Data
public class Payload <T> {
    /**
     * 载荷ID
     */
    private String id;

    /**
     * 载荷信息
     */
    private T info;

    /**
     * 过期时间
     */
    private Date expiration;

}
