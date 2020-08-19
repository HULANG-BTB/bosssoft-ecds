package com.bosssoft.ecds.entity.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public class SourceSetDto {

    @NotNull(message = "取票编码不能为空")
    @Length(min = 8, max = 8, message = "票号编码不规范")
    String billTypeCode;
    @Min(value = 1, message = "推送数量不合法")
    int pushNumber;
    @Min(value = 1, message = "阈值数量不合法")
    int minNumber;
    @Range(min = 0, max = 1, message = "字段赋值不合法")
    int enable;
    @Range(min = 0, max = 3, message = "字段赋值不合法")
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
        return "SourceSetDto{" +
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
