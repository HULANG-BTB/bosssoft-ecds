package com.bosssoft.ecds.constant;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/6 17:12
 */
public class CheckConstant {
    /**
     * 正则
     * <p>
     * 1、匹配特殊字符
     * 2、匹配整型数字
     * 3、匹配中文
     */
    public static final String SPECIAL_CHAR = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    public static final String INTEGER_REGULAR = "^[-\\+]?[\\d]*$";
    public static final String CHINESE_REGULAR = "[\\u4e00-\\u9fa5]+";


}
