package com.bosssoft.ecds.entity.dto;

public class SourceSetDto {

    String regionCode;
    int pushNumber;
    int minNumber;
    int alterCode;
    String operator;
    int operatorID;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    public int getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }

    @Override
    public String toString() {
        return "SourceSetDto{" +
                "regionCode='" + regionCode + '\'' +
                ", pushNumber=" + pushNumber +
                ", minNumber=" + minNumber +
                ", alterCode=" + alterCode +
                ", operator='" + operator + '\'' +
                ", operatorID=" + operatorID +
                '}';
    }
}
