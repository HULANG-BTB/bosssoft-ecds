package com.bosssoft.ecds.entity.po;

import java.util.Date;

public class SourceSetPo {

    int id;
    String regionCode;
    int sourceID;
    int pushNumber;
    int minNumber;
    int alterCode;
    int version;
    String operator;
    int operatorID;
    Date createDate;
    Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "SourceSetPo{" +
                "id=" + id +
                ", regionCode='" + regionCode + '\'' +
                ", sourceID=" + sourceID +
                ", pushNumber=" + pushNumber +
                ", minNumber=" + minNumber +
                ", alterCode=" + alterCode +
                ", version=" + version +
                ", operator='" + operator + '\'' +
                ", operatorID=" + operatorID +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
