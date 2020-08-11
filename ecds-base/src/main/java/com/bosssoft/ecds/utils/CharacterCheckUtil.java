package com.bosssoft.ecds.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串校验
 *
 * @author: Jianbinbing
 * @Date: 2020/8/6 15:32
 */
public class CharacterCheckUtil {

    public static boolean characterComparison(String str, String otherStr) {
        if (str.length() >= otherStr.length()) {
            return false;
        }
        String temp = StringUtils.substring(otherStr, 0, str.length());
        if (!temp.equals(str)) {
            return false;
        }
        return true;
    }
}
