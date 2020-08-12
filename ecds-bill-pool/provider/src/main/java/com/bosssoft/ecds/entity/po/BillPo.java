package com.bosssoft.ecds.entity.po;

import java.util.Date;

public class BillPo {

    private int id;
    private String regionCode;
    private String type;
    private String sort;
    private String batch;
    private String billCode;
    private int version;
    private String operator;
    private int operatorID;
    private Date createTime;
    private Date updateTime;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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
        return "BillPo{" +
                "id=" + id +
                ", regionCode='" + regionCode + '\'' +
                ", type='" + type + '\'' +
                ", sort='" + sort + '\'' +
                ", batch='" + batch + '\'' +
                ", billCode='" + billCode + '\'' +
                ", version=" + version +
                ", operator='" + operator + '\'' +
                ", operatorID=" + operatorID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
