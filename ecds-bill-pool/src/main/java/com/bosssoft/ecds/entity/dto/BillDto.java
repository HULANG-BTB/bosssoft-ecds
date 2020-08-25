package com.bosssoft.ecds.entity.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public class BillDto {

    private int id;
    @NotNull(message = "取票编码不能为空")
    @Length(min = 8, max = 8, message = "票号编码不规范")
    private String billTypeCode;
    private String regionCode;
    private String type;
    private String sort;
    private String batch;
    @NotNull(message = "票号起始编码不能为空")
    private long billCodeBegin;
    @NotNull(message = "票号结束编码不能为空")
    private long billCodeEnd;
    private int version;
    private String operator;
    private int operatorId;
    private Date createTime;
    private Date updateTime;

    public void init() {
        if (billTypeCode != null) {
            this.regionCode = billTypeCode.substring(0, 2);
            this.type = billTypeCode.substring(2, 4);
            this.sort = billTypeCode.substring(4, 6);
            this.batch = billTypeCode.substring(6, 8);
            this.operator = "admin";
            this.operatorId = -1;
        }
    }

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

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
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
                ", operatorId=" + operatorId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
