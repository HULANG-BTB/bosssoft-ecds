package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 收入情况
 */
@Data
public class WriteOffIncomeDetailDTO {

    // 收费项目编码
    private String fItemCode;

    // 收费项目名称
    private String fItemName;

    // 计量单位
    private String fUnits;

    // 开票金额
    private BigDecimal fAmt;

    // 应缴金额

    // 已缴金额

    // 欠费余额
}
