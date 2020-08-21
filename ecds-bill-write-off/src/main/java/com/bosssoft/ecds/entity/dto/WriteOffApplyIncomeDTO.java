package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hujierong
 * @date 2020-8-19
 */
@Data
public class WriteOffApplyIncomeDTO {
    /**
     * 项目编码
     */
    private String fItemCode;

    /**
     * 项目名称
     */
    private String fItemName;

    /**
     * 计量单位
     */
    private String fUnits;

    /**
     * 金额
     */
    private BigDecimal fAmt;
}
