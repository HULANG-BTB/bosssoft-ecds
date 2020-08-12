package com.bosssoft.ecds.util;

import java.util.UUID;

public class CommonUtil {

    //雪花算法
    public static long generateID() {
        SnowflakeIdWorker sf = new SnowflakeIdWorker();
        long id = sf.nextId();
        return id;
    }

    //UUID
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
