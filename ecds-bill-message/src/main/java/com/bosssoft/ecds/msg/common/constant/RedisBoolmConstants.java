package com.bosssoft.ecds.msg.common.constant;

/**
 * @author zhangxiaohui
 * @create 2020/8/22 18:12
 */
public class RedisBoolmConstants {
    private RedisBoolmConstants(){}

    /**
     * 短信查验接口的布隆过滤器在redis中的key
     * 其中bloomValue为短信查验字段 tel + verifyCode 的字符串
     */
    public static final String REDIS_BLOOM_KEY_SMS = "BLOOM_SMS";

    /**
     * 票据Id查验接口的布隆过滤器在redis中的key
     * 其中bloomValue为票据查询字段 bilId + checkCode 的字符串
     */
    public static final String REDIS_BLOOM_KEY_BILL_ID = "BLOOM_BILL_ID";
}
