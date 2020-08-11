package com.bosssoft.encodeserver;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 黄杰峰
 * @Date 2020/8/11 0011 10:05
 * @Description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Log4j2
public class redLockTest {

    String lock_key = "my_lock";
    String product = "apple";
    String flag = "fail";

    @Autowired
    private RedissonClient redissonClient;

    ThreadPoolExecutor pool = new ThreadPoolExecutor(30, 50,
            1, TimeUnit.SECONDS,
            new SynchronousQueue<>());

    @Test
    public void testRedLock() {
        // 获取可重入锁
        RLock lock = redissonClient.getLock(lock_key);
        // 红锁
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            boolean isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);
            if (isLock) {
//                int stock = Integer.parseInt(redisTemplate.opsForValue().get(product).toString());
//                if (stock <= 0) {
//                    System.out.println("fail");
//                } else {
//                    redisTemplate.opsForValue().set(product, --stock);
                    System.out.println("success");
                }
                redLock.unlock();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
