package com.bosssoft.ecds.msg.service.filter;

import com.bosssoft.ecds.msg.util.BloomFilterHelper;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangxiaohui
 * @create 2020/8/22 15:09
 */

@Service
@Slf4j
public class RedisBloomFilter {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 根据给定的布隆过滤器添加值
     */
    @Async
    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            boolean get = redisTemplate.opsForValue().getBit(key, i);
            if (!get) {
                return false;
            }
        }
        return true;
    }
}