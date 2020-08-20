package com.bosssoft.encodeserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 黄杰峰
 * @Date 2020/8/17 0017 9:38
 * @Description
 */
@RunWith(SpringRunner.class)
public class Log4j2Test {

    private static Logger log = LogManager.getLogger(Log4j2Test.class);

    @Test
    public void logTest() {
        log.info("test");
        log.warn("test");
    }
}
