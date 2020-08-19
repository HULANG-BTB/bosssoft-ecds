package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("une_cbill_item")
public class UneCbillItem {
    /**
     * 项目编码
     */
    private String fItemCode;

    /**
     * 项目名称
     */
    private String fItemName;

    /**
     * 单位
     */
    private String fUnits;

    /**
     * 金额
     */
    private double fAmt;
}
