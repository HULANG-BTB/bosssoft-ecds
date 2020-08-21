package com.bosssoft.ecds.util;

import java.util.Random;

/**
 * @author: qiuheng
 * @create: 2020-08-13 11:07
 **/
public class RandomUtil {
    public String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}
