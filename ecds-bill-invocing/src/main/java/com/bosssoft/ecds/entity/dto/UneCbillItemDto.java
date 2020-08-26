package com.bosssoft.ecds.entity.dto;

import lombok.Data;

public class UneCbillItemDto {
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
     * 数量
     */
    private int fNumber;

    /**
     * 金额
     */
    private double fAmt;

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

    public int getfNumber() {
        return fNumber;
    }

    public void setfNumber(int fNumber) {
        this.fNumber = fNumber;
    }

    public double getfAmt() {
        return fAmt;
    }

    public void setfAmt(double fAmt) {
        this.fAmt = fAmt;
    }
}
