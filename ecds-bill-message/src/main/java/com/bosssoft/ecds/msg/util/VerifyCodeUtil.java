package com.bosssoft.ecds.msg.util;

import java.util.Random;

/**
 * @author zhangxiaohui
 * @description 生成校验码的工具类
 */
public class VerifyCodeUtil {
    private VerifyCodeUtil(){}

    public static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 使用指定源生成验证码
     * @param verifySize   验证码长度
     * @param sources   验证码字符源
     * @return 返回生成的验证码
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }

}
