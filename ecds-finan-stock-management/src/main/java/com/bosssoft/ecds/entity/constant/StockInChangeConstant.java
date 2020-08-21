package com.bosssoft.ecds.entity.constant;

/**
 * 入库常量类
 *
 * @author cheng
 * @Date 2020/8/11 16:02
 */
public class StockInChangeConstant {
    /**
     * 入库变动种类
     */
    public static final Integer ADD = 1;
    public static final Integer UPDATE = 2;
    public static final Integer DELETE = 3;
    
    /**
     * 审核状态
     */
    public static final Integer UN_CHANGE = 0;
    public static final Integer PASSED = 1;
    public static final Integer UN_PASS = 2;
    
    /**
     * 入库状态
     */
    public static final Integer STORED = 1;
    public static final Integer UN_STORED = 0;
}
