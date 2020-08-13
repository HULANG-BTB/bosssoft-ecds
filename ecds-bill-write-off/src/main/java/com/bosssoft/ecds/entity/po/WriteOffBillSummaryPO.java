package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.Version;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;


@TableName("fbe_writeoff_billsummary")
@ApiModel(value="WriteOffBillsummaryPO对象")
public class WriteOffBillSummaryPO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "审验单ID")
    @TableField("f_pid")
    private String fPid;

    @ApiModelProperty(value = "序号")
    @TableField("f_sort_no")
    private Integer fSortNo;

    @ApiModelProperty(value = "票据编码")
    @TableField("f_bill_code")
    private String fBillCode;

    @ApiModelProperty(value = "票据名称")
    @TableField("f_bill_name")
    private String fBillName;

    @ApiModelProperty(value = "票据ID")
    @TableField("f_bill_id")
    private String fBillId;

    @ApiModelProperty(value = "票据代码")
    @TableField("f_batch_code")
    private String fBatchCode;

    @ApiModelProperty(value = "票面金额")
    @TableField("f_amt")
    private BigDecimal fAmt;

    @ApiModelProperty(value = "数量")
    @TableField("f_number")
    private Integer fNumber;

    @ApiModelProperty(value = "开票份数")
    @TableField("f_invnum")
    private Integer fInvNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date fCreateTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date fUpdateTime;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer fVersion;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long fOperatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String fOperator;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfPid() {
        return fPid;
    }

    public void setfPid(String fPid) {
        this.fPid = fPid;
    }

    public Integer getfSortNo() {
        return fSortNo;
    }

    public void setfSortNo(Integer fSortNo) {
        this.fSortNo = fSortNo;
    }

    public String getfBillCode() {
        return fBillCode;
    }

    public void setfBillCode(String fBillCode) {
        this.fBillCode = fBillCode;
    }

    public String getfBillName() {
        return fBillName;
    }

    public void setfBillName(String fBillName) {
        this.fBillName = fBillName;
    }

    public String getfBillId() {
        return fBillId;
    }

    public void setfBillId(String fBillId) {
        this.fBillId = fBillId;
    }

    public String getfBatchCode() {
        return fBatchCode;
    }

    public void setfBatchCode(String fBatchCode) {
        this.fBatchCode = fBatchCode;
    }

    public BigDecimal getfAmt() {
        return fAmt;
    }

    public void setfAmt(BigDecimal fAmt) {
        this.fAmt = fAmt;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public Integer getfInvNum() {
        return fInvNum;
    }

    public void setfInvNum(Integer fInvNum) {
        this.fInvNum = fInvNum;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    public Integer getfVersion() {
        return fVersion;
    }

    public void setfVersion(Integer fVersion) {
        this.fVersion = fVersion;
    }

    public Long getfOperatorId() {
        return fOperatorId;
    }

    public void setfOperatorId(Long fOperatorId) {
        this.fOperatorId = fOperatorId;
    }

    public String getfOperator() {
        return fOperator;
    }

    public void setfOperator(String fOperator) {
        this.fOperator = fOperator;
    }
}
