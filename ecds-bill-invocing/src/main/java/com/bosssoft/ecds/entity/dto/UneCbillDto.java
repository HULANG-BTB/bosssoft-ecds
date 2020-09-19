package com.bosssoft.ecds.entity.dto;

import lombok.Data;

public class UneCbillDto {
    /**
     * 票据代码
     */
    private String fBillId;

    /**
     *  票据号码
     */
    private String fBillNo;

    /**
     * 票据类型
     */
    private String fType;

    /**
     * 校验码
     */
    private String checkCode;

    public String getfBillId() {
        return fBillId;
    }

    public void setfBillId(String fBillId) {
        this.fBillId = fBillId;
    }

    public String getfBillNo() {
        return fBillNo;
    }

    public void setfBillNo(String fBillNo) {
        this.fBillNo = fBillNo;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
