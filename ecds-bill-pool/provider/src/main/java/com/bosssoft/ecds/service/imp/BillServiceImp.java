package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.BillDao;
import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;
import com.bosssoft.ecds.entity.po.BillPo;
import com.bosssoft.ecds.entity.vo.BillVo;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.utils.BeanUtils;
import com.bosssoft.ecds.utils.FanoutRabbitUtils;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BillServiceImp implements BillService {

    private final String LOCK_KEY = "lock_key";
    private final Integer THRESHOLD = 3000;

    private static final Logger logger = LoggerFactory.getLogger(BillServiceImp.class);

    @Resource
    BillDao billDao;

    @Resource
    RedisTemplate<String, Integer> redisTemplate;

    @Resource
    RedissonClient redissonClient;

    @Resource
    FanoutRabbitUtils fanoutRabbitUtils;

    @Override
    public List retrieveBill(RetrieveBillDto retrieveBillDto) {

        int remainderBill;
        int deleteNumber = 0;
        boolean isLock;
        List<BillPo> billPoList = new LinkedList<>();
        List<BillVo> billVoList = new LinkedList<>();
        List<Integer> deleteList = new LinkedList<>();

        String table = (String) redisTemplate.opsForHash().get(retrieveBillDto.getBillTypeCode(), "table");

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                remainderBill = (int) redisTemplate.opsForHash().get("remainderBill", retrieveBillDto.getBillTypeCode());

                if (remainderBill < retrieveBillDto.getNumber()) {
                    fanoutRabbitUtils.sendBillExhaust(retrieveBillDto.getBillTypeCode());
                    return null;
                }

                billPoList = billDao.retrieveList(table, (int) retrieveBillDto.getNumber());

                for (int i = 0; i < billPoList.size(); i++) {
                    deleteList.add(billPoList.get(i).getId());
                }

                deleteNumber = billDao.deleteList(table, deleteList);

                remainderBill = billDao.retrieveNumber(table);

                redisTemplate.opsForHash().put("remainderBill", retrieveBillDto.getBillTypeCode(), remainderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }

        logger.info(deleteNumber + "张票据已出库");

        billVoList = BeanUtils.convertList(billPoList, BillVo.class);

        return billVoList;
    }

    @Override
    public int createBill(BillDto billDto) {

        int createNumber = 0;
        int remainderBill;
        boolean isLock;
        String table;
        Map map = new HashMap();
        List<BillPo> billPoList = new ArrayList<>();

        table = (String) redisTemplate.opsForHash().get(billDto.getBillTypeCode(), "table");

        for (long i = billDto.getBillCodeBegin(); i <= billDto.getBillCodeEnd(); i++) {
            BillPo billPo = BeanUtils.convertObject(billDto, BillPo.class);
            billPo.setBillCode(String.valueOf(i));
            billPoList.add(billPo);
        }

        RLock lock = redissonClient.getLock(LOCK_KEY);
        RedissonRedLock redLock = new RedissonRedLock(lock);

        try {
            isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);

            if (isLock) {
                createNumber = billDao.insertBill(table, billPoList);

                remainderBill = billDao.retrieveNumber(table);
                if (redisTemplate.hasKey("remainderBill")) {
                    map = redisTemplate.opsForHash().entries("remainderBill");
                }

                map.put(billDto.getBillTypeCode(), remainderBill);

                redisTemplate.opsForHash().putAll("remainderBill", map);
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
