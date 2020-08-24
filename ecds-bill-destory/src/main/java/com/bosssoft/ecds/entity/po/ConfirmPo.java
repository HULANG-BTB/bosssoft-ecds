package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * @author: qiuheng
 * @create: 2020-08-21 09:50
 **/
@TableName("ube_destory_confirm")
public class ConfirmPo {

    /**
     * 主键
     */
    @TableId(value = "f_id")
    private Long fId;

    /*
     *版本号
     */
    private int fVersion;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 修改时间
     */
    private LocalDateTime fUpdateTime;

    /*
     *审核时间
     */
    private LocalDateTime fConfirmTime;

    /*
     *审核结果
     */
    private boolean fResult;

    /*
     *审核备注
     */
    private String fConfirmMemo;

    /*
     *票据销毁申请单号
     */
    private String fDestroyNo;

    /*
     *审核人
     */
    private String fConfirmMan;

    /**
     * 操作人id
     */
    private Long fOperatorId;

    /**
     * 操作人
     */
    private String fOperatorName;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public int getfVersion() {
        return fVersion;
    }

    public void setfVersion(int fVersion) {
        this.fVersion = fVersion;
    }

    public LocalDateTime getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(LocalDateTime fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public LocalDateTime getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(LocalDateTime fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    public LocalDateTime getfConfirmTime() {
        return fConfirmTime;
    }

    public void setfConfirmTime(LocalDateTime fConfirmTime) {
        this.fConfirmTime = fConfirmTime;
    }

    public boolean isfResult() {
        return fResult;
    }

    public void setfResult(boolean fResult) {
        this.fResult = fResult;
    }

    public String getfReason() {
        return fConfirmMemo;
    }

    public void setfReason(String fReason) {
        this.fConfirmMemo = fConfirmMemo;
    }

    public String getfDestroyNo() {
        return fDestroyNo;
    }

    public void setfDestroyNo(String fDestroyNo) {
        this.fDestroyNo = fDestroyNo;
    }

    public String getfConfirmMan() {
        return fConfirmMan;
    }

    public void setfConfirmMan(String fConfirmMan) {
        this.fConfirmMan = fConfirmMan;
    }

    public Long getfOperatorId() {
        return fOperatorId;
    }

    public void setfOperatorId(Long fOperatorId) {
        this.fOperatorId = fOperatorId;
    }

    public String getfOperatorName() {
        return fOperatorName;
    }

    public void setfOperatorName(String fOperatorName) {
        this.fOperatorName = fOperatorName;
    }
}
