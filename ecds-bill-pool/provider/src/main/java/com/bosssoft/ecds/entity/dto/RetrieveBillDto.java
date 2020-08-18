package com.bosssoft.ecds.entity.dto;

import java.util.Date;

public class RetrieveBillDto {

    private String billTypeCode;
    private long number;
    private String operator;
    private int operatorID;
    private Date createTime;
    private Date updateTime;

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RetrieveBillDto{" +
                "billTypeCode='" + billTypeCode + '\'' +
                ", number=" + number +
                ", operator='" + operator + '\'' +
                ", operatorID=" + operatorID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
