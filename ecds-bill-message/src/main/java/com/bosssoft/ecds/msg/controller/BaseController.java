package com.bosssoft.ecds.msg.controller;


import com.bosssoft.ecds.msg.common.constant.RedisBoolmConstants;
import com.bosssoft.ecds.msg.service.filter.RedisBloomFilter;
import com.bosssoft.ecds.msg.util.BloomFilterHelper;
import com.bosssoft.ecds.response.ResponseResult;

import javax.annotation.Resource;

/**
 * @author zhangxiaohui
 * @create 2020/8/19 19:34
 */
public class BaseController {

    @Resource
    protected RedisBloomFilter redisBloomFilter;

    @Resource
    protected BloomFilterHelper<String> bloomFilterHelper;

    /**
     * 短信查验接口的布隆过滤器在redis中的key
     * 其中bloomValue为短信查验字段 tel + verifyCode 的字符串
     */
    protected static final String REDIS_BLOOM_KEY_SMS = RedisBoolmConstants.REDIS_BLOOM_KEY_SMS;

    /**
     * 票据Id查验接口的布隆过滤器在redis中的key
     * 其中bloomValue为票据查询字段 bilId + checkCode 的字符串
     */
    protected static final String REDIS_BLOOM_KEY_BILL_ID = RedisBoolmConstants.REDIS_BLOOM_KEY_BILL_ID;


    public ResponseResult getRes(boolean b) {
        if (b) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }

}
