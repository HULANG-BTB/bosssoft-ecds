package com.bosssoft.ecds.entity.dto;

import com.bosssoft.ecds.entity.po.ItemPo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: qiuheng
 * @create: 2020-08-12 17:06
 **/

public class ApplyDto implements Serializable {
    /**
     * 单号
     */
    private Long fDestroyNo;

    /**
     * 单位识别码
     */
    private String fAgenIdCode;

    /*
     *单位名称
     */
    private String fUnitName;

    /**
     * 销毁类型：0.核销票据销毁；1.库存票据销毁
     */
    private Boolean fDestroyType;

    /**
     * 备注
     */
    private String fDestroyMemo;

    /**
     * 申请日期
     */
    private String fApplyDate;

    /**
     * 申请人
     */
    private String fApplyMan;

    /**
     * 审核状态：0.未审核；1.已审核但未通过；2.已审核并通过
     */
    private int fStatus;

    /**
     * 操作人id
     */
    private Long fOperatorId;

    /**
     * 操作人
     */
    private String fOperatorName;

    /**
     * 区划编码
     */
    private String fRgnCode;

    public Long getfDestroyNo() {
        return fDestroyNo;
    }

    public String getfAgenIdCode() {
        return fAgenIdCode;
    }

    public Boolean getfDestroyType() {
        return fDestroyType;
    }

    public String getfDestroyMemo() {
        return fDestroyMemo;
    }


    public String getfApplyMan() {
        return fApplyMan;
    }

    public int getfStatus() {
        return fStatus;
    }

    public Long getfOperatorId() {
        return fOperatorId;
    }

    public String getfOperatorName() {
        return fOperatorName;
    }

    public String getfRgnCode() {
        return fRgnCode;
    }

    public void setfDestroyNo(Long fDestroyNo) {
        this.fDestroyNo = fDestroyNo;
    }

    public void setfAgenIdCode(String fAgenIdCode) {
        this.fAgenIdCode = fAgenIdCode;
    }

    public String getfUnitName() {
        return fUnitName;
    }

    public void setfUnitName(String fUnitName) {
        this.fUnitName = fUnitName;
    }

    public void setfDestroyType(Boolean fDestroyType) {
        this.fDestroyType = fDestroyType;
    }

    public void setfDestroyMemo(String fDestroyMemo) {
        this.fDestroyMemo = fDestroyMemo;
    }

    public void setfApplyMan(String fApplyMan) {
        this.fApplyMan = fApplyMan;
    }

    public String getfApplyDate() {
        return fApplyDate;
    }

    public void setfApplyDate(String fApplyDate) {
        this.fApplyDate = fApplyDate;
    }

    public void setfStatus(int fStatus) {
        this.fStatus = fStatus;
    }

    public void setfOperatorId(Long fOperatorId) {
        this.fOperatorId = fOperatorId;
    }

    public void setfOperatorName(String fOperatorName) {
        this.fOperatorName = fOperatorName;
    }

    public void setfRgnCode(String fRgnCode) {
        this.fRgnCode = fRgnCode;
    }
}
