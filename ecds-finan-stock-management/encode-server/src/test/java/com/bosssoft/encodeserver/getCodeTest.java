package com.bosssoft.encodeserver;

import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.service.GetCodeService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 黄杰峰
 * @Date 2020/8/10 0010 14:38
 * @Description
 */
@Log4j2
@SpringBootTest
@RunWith(SpringRunner.class)
public class getCodeTest {

    @Autowired
    private GetCodeService getCodeService;

    ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 10,
            1, TimeUnit.SECONDS,
            new SynchronousQueue<>());

    @Test
    public void getCode() {

        GetFinanceNumDto getFinanceNumDTO = new GetFinanceNumDto();
        getFinanceNumDTO.setFRegiId("35");
        getFinanceNumDTO.setFSortId("02");
        getFinanceNumDTO.setFTypeId("02");
        getFinanceNumDTO.setFAnnualId("20");
        getFinanceNumDTO.setCodeNum(1000);
        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDTO);
        log.info(batchCode);
    }

    @Test
    public void getCodeTest() {
        GetFinanceNumDto getFinanceNumDTO = new GetFinanceNumDto();
        getFinanceNumDTO.setFRegiId("35");
        getFinanceNumDTO.setFSortId("03");
        getFinanceNumDTO.setFTypeId("03");
        getFinanceNumDTO.setFAnnualId("20");
        getFinanceNumDTO.setCodeNum(1000);

        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDTO);
        log.info(batchCode);
    }

    @Test
    public void getCodeRedLockTest() {
        GetFinanceNumDto getFinanceNumDTO = new GetFinanceNumDto();
        getFinanceNumDTO.setFRegiId("35");
        getFinanceNumDTO.setFSortId("01");
        getFinanceNumDTO.setFTypeId("02");
        getFinanceNumDTO.setFAnnualId("20");
        getFinanceNumDTO.setCodeNum(1000);

        for (int i = 0; i < 30; i++) {
            log.info(i);
            pool.submit(() -> {
                getCodeService.getBatchCode(getFinanceNumDTO);
            });

            //当线程池中的线程数为0时，退出
            while (pool.getPoolSize() != 0) {}
        }
    }

}
