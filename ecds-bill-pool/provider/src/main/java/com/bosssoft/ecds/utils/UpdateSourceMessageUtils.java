package com.bosssoft.ecds.utils;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.dao.SourceSetDao;
import com.bosssoft.ecds.entity.po.SourceMessagePo;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class UpdateSourceMessageUtils {

    private static final String LOCK_KEY = "Source_Set";

    @Resource
    SourceSetDao sourceSetDao;

    @Resource
    BillDao billDao;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    RedissonClient redissonClient;

    public void update() {

        boolean isLock;
        List<SourceMessagePo> list;
        list = sourceSetDao.retrieveSourceMessageList();

        List<String> stringList = billDao.retrieveRegionCode();

        redisTemplate.delete("RegionCode");
        redisTemplate.opsForList().leftPushAll("RegionCode", stringList);

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                for (int i = 0; i < list.size(); i++) {
                    redisTemplate.delete(list.get(i).getRegionCode());
                    Map map = new HashMap();
                    map.put("table", list.get(i).getTable());
                    map.put("threshold", list.get(i).getThreshold());
                    redisTemplate.opsForHash().putAll(list.get(i).getRegionCode(), map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }

    public void update(String regionCode) {

        boolean isLock;
        SourceMessagePo sourceMessagePo = sourceSetDao.retrieveSourceMessageByCode(regionCode);

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                Map map = new HashMap();
                map.put("table", sourceMessagePo.getTable());
                map.put("threshold", sourceMessagePo.getThreshold());
                redisTemplate.delete(sourceMessagePo.getRegionCode());
                redisTemplate.opsForHash().putAll(sourceMessagePo.getRegionCode(), map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }
}
