package com.bosssoft.ecds.entity.constant;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

/**
 * 出库常量类
 *
 * @author misheep
 * @date 2020/8/12 20:14
 */
public class StockOutConstant {
    /**
     * changeState审核状态(0保存1提交2通过3退回）
     */
    public static final Integer STATE_NEW = 0;
    public static final Integer SAVE = 1;
    public static final Integer UN_CHANGE = 2;
    public static final Integer PASSED = 3;
    public static final Integer UN_PASS = 4;

    /**
     * altercode变更状态(0无用1新增2修改3删除)
     */
    public static final Integer ALTER_NO = 0;
    public static final Integer ALTER_NEW = 1;
    public static final Integer ALTER_EDIT = 2;
    public static final Integer ALTER_DELETE = 3;
}
