package com.bosssoft.ecds.utils;

import org.apache.commons.lang3.StringUtils;


/**
 * 字符串校验
 *
 * @author: Jianbinbing
 * @Date: 2020/8/6 15:32
 */
public class CharacterCheckUtil {

    /**
     * 比较两个字符串是否是包含关系
     *
     * @param str
     * @param otherStr
     * @return
     */
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
