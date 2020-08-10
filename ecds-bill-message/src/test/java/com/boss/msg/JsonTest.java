package com.boss.msg;

import com.alibaba.fastjson.JSON;
import com.boss.msg.entity.dto.VerifyCode;

/**
 * @author zhangxiaohui
 * @create 2020/8/7 16:42
 */
public class JsonTest {
    public static void main(String[] args) {
        System.out.println(f(new VerifyCode("cji1221d")));
    }
    public static String f(Object obj){
        return JSON.toJSONString(obj);
    }
}
