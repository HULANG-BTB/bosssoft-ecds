package com.bosssoft.ecds.msg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangxiaohui
 * @create 2020/8/13 16:05
 */
public class TestApp {
    public static void main(String[] args) {
        String verifyCodeRegex ="^[A-Za-z0-9]{6}$";
        System.out.println(Pattern.matches(verifyCodeRegex, "i7gxlo"));
    }
}
