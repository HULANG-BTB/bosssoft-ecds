package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.po.BillPo;
import com.bosssoft.ecds.entity.vo.BillVo;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.utils.BeanUtils;
import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BillServiceImp implements BillService {

    private final String LOCK_KEY = "lock_key";
    private final Integer THRESHOLD = 3000;

    @Resource
    BillDao billDao;

    @Resource
    RedisTemplate<String, Integer> redisTemplate;

    @Resource
    RedissonClient redissonClient;

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @Override
    public List retrieveBill(int number) {

        int remainderBill;
        int deleteNumber;
        boolean isLock;
        List<BillPo> billPoList = new LinkedList<>();
        List<BillVo> billVoList = new LinkedList<>();
        List<Integer> deleteList = new LinkedList<>();

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                remainderBill = redisTemplate.opsForValue().get("remainderBill");

                if (remainderBill < number) {
                    fanoutRabbitUtils.sendBillExhaust();
                    return null;
                }

                billPoList = billDao.retrieveList(number);

                for (int i = 0; i < billPoList.size(); i++) {
                    deleteList.add(billPoList.get(i).getId());
                }

                deleteNumber = billDao.deleteList(deleteList);

                remainderBill = billDao.retrieveNumber("fbb_billpool");

                redisTemplate.opsForValue().set("remainderBill", remainderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }

        billVoList = BeanUtils.convertList(billPoList, BillVo.class);

        return billVoList;
    }

    @Override
    public int createBill(List<BillDto> list) {

        int createNumber = 0;
        int remainderBill;
        boolean isLock;
        String table;
        List<BillPo> billPoList;

        table = (String) redisTemplate.opsForHash().get(list.get(0).getRegionCode(), "table");
        billPoList = BeanUtils.convertList(list, BillPo.class);

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                createNumber = billDao.insertBill(table, billPoList);

                remainderBill = billDao.retrieveNumber(table);

                redisTemplate.opsForValue().set("remainderBill", remainderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }

        return createNumber;
    }

    @Override
    public int retrieveNumber(String table) {

        boolean isLock;
        int remainderBill = -1;

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                remainderBill = billDao.retrieveNumber(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }

        return remainderBill;
    }
}
