package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hujierong
 * @date 2020-8-14
 */
public class WriteOffApplyVO {
    /**
     * 业务单号
     */
    private String fNo;

    /**
     * 编制人
     */
    private String fAuthor;

    /**
     * 截止日期
     */
    private Date fEndDate;

    /**
     * 编制日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date fDate;

    /**
     * 合计份数
     */
    private Integer fNumber;

    /**
     * 总金额
     */
    private BigDecimal fTotalAmt;

    /**
     * 备注
     */
    private String fMemo;

    /**
     * 状态： 1 未审验 2 已审验
     */
    private String fChangeState;

    /**
     * 审验结果：1 良好 2 合格 3 问题 4 整改通过
     */
    private String fCheckResult;

    /**
     * 是否上报：1 未上报 2 已上报
     */
    private String fIsUpload;

    public String getfNo() {
        return fNo;
    }

    public void setfNo(String fNo) {
        this.fNo = fNo;
    }

    public String getfAuthor() {
        return fAuthor;
    }

    public void setfAuthor(String fAuthor) {
        this.fAuthor = fAuthor;
    }

    public Date getfEndDate() {
        return fEndDate;
    }

    public void setfEndDate(Date fEndDate) {
        this.fEndDate = fEndDate;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public BigDecimal getfTotalAmt() {
        return fTotalAmt;
    }

    public void setfTotalAmt(BigDecimal fTotalAmt) {
        this.fTotalAmt = fTotalAmt;
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo;
    }

    public String getfChangeState() {
        return fChangeState;
    }

    public void setfChangeState(String fChangeState) {
        this.fChangeState = fChangeState;
    }

    public String getfCheckResult() {
        return fCheckResult;
    }

    public void setfCheckResult(String fCheckResult) {
        this.fCheckResult = fCheckResult;
    }

    public String getfIsUpload() {
        return fIsUpload;
    }

    public void setfIsUpload(String fIsUpload) {
        this.fIsUpload = fIsUpload;
    }
}
