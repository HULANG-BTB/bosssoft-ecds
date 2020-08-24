package com.bosssoft.ecds.msg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.msg.common.constant.RedisBoolmConstants;
import com.bosssoft.ecds.msg.entity.po.SmsPo;
import com.bosssoft.ecds.msg.mapper.SmsMapper;
import com.bosssoft.ecds.msg.service.filter.RedisBloomFilter;
import com.bosssoft.ecds.msg.util.BloomFilterHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SmsApplicationTests {

    @Resource
    protected RedisBloomFilter redisBloomFilter;

    @Resource
    protected BloomFilterHelper<String> bloomFilterHelper;


    @Resource
    SmsMapper smsMapper;

    @Test
    void contextLoads() throws InterruptedException {
        QueryWrapper<SmsPo> query = new QueryWrapper<>();
        List<SmsPo> smsPos = smsMapper.selectList(query);
        for (SmsPo smsPo : smsPos) {
            String s = smsPo.getSmsTo() + smsPo.getVerifyCode();
            System.out.println(s);
            // Thread.sleep(1000)
            redisBloomFilter.addByBloomFilter(bloomFilterHelper, RedisBoolmConstants.REDIS_BLOOM_KEY_SMS, s);
        }
    }

}
