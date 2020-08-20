package com.bosssoft.ecds.entity.constant;

/**
 * 出库常量类
 *
 * @Author misheep
 * @Date 2020/8/12 20:14
 */
public class StockOutChangeConstant {
    /**
     * 审核状态(0保存1提交2通过3退回）
     */
    public static final Integer NEW_ADD = 0;
    public static final Integer SAVE = 1;
    public static final Integer UN_CHANGE = 2;
    public static final Integer PASSED = 3;
    public static final Integer UN_PASS = 4;
}
