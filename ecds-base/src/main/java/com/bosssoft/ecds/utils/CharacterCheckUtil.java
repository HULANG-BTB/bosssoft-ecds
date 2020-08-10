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

    public static final String SPECIAL_CHAR = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    public static final String INTEGER_REGULAR = "^[-\\+]?[\\d]*$";
    public static final String CHINESE_REGULAR = "[\\u4e00-\\u9fa5]+";

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        Pattern p = Pattern.compile(SPECIAL_CHAR);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断String是否是数字型
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile(INTEGER_REGULAR);
        return pattern.matcher(str).matches();
    }


    /**
     * 校验String是否全是中文
     *
     * @param str
     * @return
     */
    public static boolean checkNameChinese(String str) {
        Pattern pattern = Pattern.compile(CHINESE_REGULAR);
        return pattern.matcher(str).matches();
    }

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
