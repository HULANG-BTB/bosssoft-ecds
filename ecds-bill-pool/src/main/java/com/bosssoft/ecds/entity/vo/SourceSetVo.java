package com.bosssoft.ecds.entity.vo;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public class SourceSetVo {

    String billTypeCode;
    int pushNumber;
    int minNumber;
    int enable;
    int alterCode;
    String operator;
    int operatorId;

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

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getAlterCode() {
        return alterCode;
    }

    public void setAlterCode(int alterCode) {
        this.alterCode = alterCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "SourceSetVo{" +
                "billTypeCode='" + billTypeCode + '\'' +
                ", pushNumber=" + pushNumber +
                ", minNumber=" + minNumber +
                ", enable=" + enable +
                ", alterCode=" + alterCode +
                ", operator='" + operator + '\'' +
                ", operatorId=" + operatorId +
                '}';
    }
}
