package com.bosssoft.ecds.utils;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.dao.SourceSetDao;
import com.bosssoft.ecds.entity.po.SourceMessagePo;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UpdateSourceMessageUtils.class);

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

        List<String> stringList = billDao.retrieveBillTypeCode();

        redisTemplate.delete("billTypeCode");
        redisTemplate.opsForList().leftPushAll("billTypeCode", stringList);

        logger.info("更新billTypeCode列表:" + stringList);

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                for (int i = 0; i < list.size(); i++) {
                    redisTemplate.delete(list.get(i).getBillTypeCode());
                    Map map = new HashMap();
                    map.put("table", list.get(i).getTable());
                    map.put("threshold", list.get(i).getThreshold());
                    redisTemplate.opsForHash().putAll(list.get(i).getBillTypeCode(), map);
                    logger.info("更新billTypeCode对象:" + list.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }

    public void update(String billTypeCode) {

        boolean isLock;

        SourceMessagePo sourceMessagePo = sourceSetDao.retrieveSourceMessageByCode(billTypeCode);

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                Map map = new HashMap();
                map.put("table", sourceMessagePo.getTable());
                map.put("threshold", sourceMessagePo.getThreshold());
                redisTemplate.delete(sourceMessagePo.getBillTypeCode());
                redisTemplate.opsForHash().putAll(sourceMessagePo.getBillTypeCode(), map);
                logger.info("更新billTypeCode对象"
                        + redisTemplate.opsForHash().entries(sourceMessagePo.getBillTypeCode()).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }
}
