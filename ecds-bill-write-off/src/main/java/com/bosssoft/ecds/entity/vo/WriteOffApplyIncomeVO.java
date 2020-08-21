package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hujierong
 * @date 2020-8-19
 */
public class WriteOffApplyIncomeVO {
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

    public String getfItemCode() {
        return fItemCode;
    }

    public void setfItemCode(String fItemCode) {
        this.fItemCode = fItemCode;
    }

    public String getfItemName() {
        return fItemName;
    }

    public void setfItemName(String fItemName) {
        this.fItemName = fItemName;
    }

    public String getfUnits() {
        return fUnits;
    }

    public void setfUnits(String fUnits) {
        this.fUnits = fUnits;
    }

    public BigDecimal getfAmt() {
        return fAmt;
    }

    public void setfAmt(BigDecimal fAmt) {
        this.fAmt = fAmt;
    }
}
