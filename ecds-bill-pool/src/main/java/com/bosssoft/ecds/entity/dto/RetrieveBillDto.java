package com.bosssoft.ecds.entity.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public class RetrieveBillDto {

    @NotNull(message = "取票编码不能为空")
    @Length(min = 8, max = 8, message = "票号编码不规范")
    private String billTypeCode;
    @Min(value = 1, message = "取票数量不规范")
    private long number;
    private String operator;
    private int operatorId;
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
        return "RetrieveBillDto{" +
                "billTypeCode='" + billTypeCode + '\'' +
                ", number=" + number +
                ", operator='" + operator + '\'' +
                ", operatorId=" + operatorId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
