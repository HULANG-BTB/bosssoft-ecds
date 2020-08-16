package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@TableName("fbe_writeoff")
@ApiModel(value="WriteOffPO对象")
public class WriteOffPO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_code")
    private String fRgnCode;

    @ApiModelProperty(value = "业务单号")
    @TableField("f_no")
    private String fNo;

    @ApiModelProperty(value = "单位识别码")
    @TableField("f_agen_id_code")
    private String fAgenIdCode;

    @ApiModelProperty(value = "审验日期")
    @TableField("f_check_date")
    private Date fCheckDate;

    @ApiModelProperty(value = "编制人")
    @TableField("f_author")
    private String fAuthor;

    @ApiModelProperty(value = "编制日期")
    @TableField("f_date")
    private Date fDate;

    @ApiModelProperty(value = "审验结果：1 良好 2 合格 3 问题 4 整改通过")
    @TableField("f_check_result")
    private Short fCheckResult;

    @ApiModelProperty(value = "备注")
    @TableField("f_memo")
    private String fMemo;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer fVersion;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date fCreateTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date fUpdateTime;

    @ApiModelProperty(value = "状态：1 未审验，2 已审验")
    @TableField("f_change_state")
    private short fChangeState;

    @ApiModelProperty(value = "审验申请单业务唯一号")
    @TableField("f_apply_id")
    private String fApplyId;

    @ApiModelProperty(value = "接收数据批次号")
    @TableField("f_batch_no")
    private Integer fBatchNo;

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

    public String getfRgnCode() {
        return fRgnCode;
    }

    public void setfRgnCode(String fRgnCode) {
        this.fRgnCode = fRgnCode;
    }

    public String getfNo() {
        return fNo;
    }

    public void setfNo(String fNo) {
        this.fNo = fNo;
    }

    public String getfAgenIdCode() {
        return fAgenIdCode;
    }

    public void setfAgenIdCode(String fAgenIdCode) {
        this.fAgenIdCode = fAgenIdCode;
    }

    public Date getfCheckDate() {
        return fCheckDate;
    }

    public void setfCheckDate(Date fCheckDate) {
        this.fCheckDate = fCheckDate;
    }

    public String getfAuthor() {
        return fAuthor;
    }

    public void setfAuthor(String fAuthor) {
        this.fAuthor = fAuthor;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public Short getfCheckResult() {
        return fCheckResult;
    }

    public void setfCheckResult(Short fCheckResult) {
        this.fCheckResult = fCheckResult;
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo;
    }

    public Integer getfVersion() {
        return fVersion;
    }

    public void setfVersion(Integer fVersion) {
        this.fVersion = fVersion;
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

    public short getfChangeState() {
        return fChangeState;
    }

    public void setfChangeState(short fChangeState) {
        this.fChangeState = fChangeState;
    }

    public String getfApplyId() {
        return fApplyId;
    }

    public void setfApplyId(String fApplyId) {
        this.fApplyId = fApplyId;
    }

    public Integer getfBatchNo() {
        return fBatchNo;
    }

    public void setfBatchNo(Integer fBatchNo) {
        this.fBatchNo = fBatchNo;
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
