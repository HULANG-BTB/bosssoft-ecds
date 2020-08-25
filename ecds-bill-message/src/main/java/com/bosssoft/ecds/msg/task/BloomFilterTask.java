package com.bosssoft.ecds.msg.task;

import com.bosssoft.ecds.msg.service.filter.RedisBloomFilter;
import com.bosssoft.ecds.msg.util.BloomFilterHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author zhangxiaohui
 * @create 2020/8/22 18:11
 */

@Slf4j
//@Component
public class BloomFilterTask {

    @Resource
    protected RedisBloomFilter redisBloomFilter;

    @Resource
    protected BloomFilterHelper<String> bloomFilterHelper;

    /**
     * 定时更新布隆过滤器中的数据
     */
    @Async
    @Scheduled(cron = "* * * 1 * ?")
    public void updateBloomFilter() {
        log.info("线程 >>> " + Thread.currentThread().getName() + " --- 布隆过滤器更新任务");
    }


}
