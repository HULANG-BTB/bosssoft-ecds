package com.bosssoft.ecds.util;

public class CommonUtil {

    private CommonUtil() {}

    //雪花算法
    public static long generateID() {
        SnowflakeIdWorker sf = new SnowflakeIdWorker();
        return sf.nextId();
    }
}
