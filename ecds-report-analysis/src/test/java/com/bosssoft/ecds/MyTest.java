package com.bosssoft.ecds;

import com.bosssoft.ecds.dao.FabFinanBillDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author syf
 * @Date 2020/8/13 15:01
 */
@SpringBootTest(classes = ReportApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MyTest {

    @Autowired
    private FabFinanBillDao fabFinanBillDao;

    @Test
    public void test(){
        System.out.println(fabFinanBillDao);
    }

}
