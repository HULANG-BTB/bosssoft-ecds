package com.bosssoft.ecds.entity.vo;

public class SourceSetVo {

    String billTypeCode;
    int pushNumber;
    int minNumber;

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public int getPushNumber() {
        return pushNumber;
    }

    public void setPushNumber(int pushNumber) {
        this.pushNumber = pushNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    @Override
    public String toString() {
        return "SourceSetVo{" +
                "billTypeCode='" + billTypeCode + '\'' +
                ", pushNumber=" + pushNumber +
                ", minNumber=" + minNumber +
                '}';
    }
}
