package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hujierong
 * @date 2020-8-17
 */
public class WriteOffApplyItemVO {
    /**
     * 票据编码
     */
    private String fBillCode;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 票据批次号
     */
    private String fBatchNo;

    /**
     * 开票份数
     */
    private Integer fNumber;

    /**
     * 票据起始号
     */
    private String fBillNo1;

    /**
     * 票据结束号
     */
    private String fBillNo2;

    /**
     * 票面金额
     */
    private BigDecimal fAmt;

    public String getfBillCode() {
        return fBillCode;
    }

    public void setfBillCode(String fBillCode) {
        this.fBillCode = fBillCode;
    }

    public String getfBillName() {
        return fBillName;
    }

    public void setfBillName(String fBillName) {
        this.fBillName = fBillName;
    }

    public String getfBatchNo() {
        return fBatchNo;
    }

    public void setfBatchNo(String fBatchNo) {
        this.fBatchNo = fBatchNo;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public String getfBillNo1() {
        return fBillNo1;
    }

    public void setfBillNo1(String fBillNo1) {
        this.fBillNo1 = fBillNo1;
    }

    public String getfBillNo2() {
        return fBillNo2;
    }

    public void setfBillNo2(String fBillNo2) {
        this.fBillNo2 = fBillNo2;
    }

    public BigDecimal getfAmt() {
        return fAmt;
    }

    public void setfAmt(BigDecimal fAmt) {
        this.fAmt = fAmt;
    }
}
