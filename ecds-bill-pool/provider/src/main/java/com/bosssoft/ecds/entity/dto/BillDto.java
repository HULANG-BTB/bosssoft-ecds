package com.bosssoft.ecds.entity.dto;

import java.util.Date;

public class BillDto {

    private int id;
    private String billTypeCode;
    private String regionCode;
    private String type;
    private String sort;
    private String batch;
    private long billCodeBegin;
    private long billCodeEnd;
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

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
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

    public long getBillCodeBegin() {
        return billCodeBegin;
    }

    public void setBillCodeBegin(long billCodeBegin) {
        this.billCodeBegin = billCodeBegin;
    }

    public long getBillCodeEnd() {
        return billCodeEnd;
    }

    public void setBillCodeEnd(long billCodeEnd) {
        this.billCodeEnd = billCodeEnd;
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
        return "BillDto{" +
                "id=" + id +
                ", billTypeCode='" + billTypeCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", type='" + type + '\'' +
                ", sort='" + sort + '\'' +
                ", batch='" + batch + '\'' +
                ", billCodeBegin=" + billCodeBegin +
                ", billCodeEnd=" + billCodeEnd +
                ", version=" + version +
                ", operator='" + operator + '\'' +
                ", operatorID=" + operatorID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
