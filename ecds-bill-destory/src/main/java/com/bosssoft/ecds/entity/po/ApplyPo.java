package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
//flase 不调用父类的属性
//@EqualsAndHashCode(callSuper = false)
//控制getter和setter方法形式，set方法返回的是对象名称，更加的直观，适合对象赋值时的连续赋值参数
//@Accessors(chain = true)
@TableName("ube_destroy_apply")
public class ApplyPo{

    /**
     * 主键
     */
    @TableId(value = "f_id")
    private Long fId;

    /**
     * 单号
     */
    private Long fDestroyNo;

    /**
     * 区划编码
     */
    private String fRgnCode;

    /**
     * 单位识别码
     */
    private String fAgenIdCode;

    /*
     *单位名称
     */
    private String fUnitName;

    /**
     * 备注
     */
    private String fDestroyMemo;

    /**
     * 版本号
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer fVersion;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 修改时间
     */
    private LocalDateTime fUpdateTime;

    /**
     * 操作人id
     */
    private Long fOperatorId;

    /**
     * 操作人
     */
    private String fOperatorName;

    /**
     * 申请日期
     */
    private String fApplyDate;

    /**
     * 申请人
     */
    private String fApplyMan;

    /**
     * 销毁类型：0.核销票据销毁；1.库存票据销毁
     */
    private Boolean fDestroyType;

    /**
     * 审核状态：0.未审核；1.已审核
     */
    private int fStatus;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getfDestroyNo() {
        return fDestroyNo;
    }

    public void setfDestroyNo(Long fDestroyNo) {
        this.fDestroyNo = fDestroyNo;
    }

    public String getfRgnCode() {
        return fRgnCode;
    }

    public void setfRgnCode(String fRgnCode) {
        this.fRgnCode = fRgnCode;
    }

    public String getfAgenIdCode() {
        return fAgenIdCode;
    }

    public String getfUnitName() {
        return fUnitName;
    }

    public void setfUnitName(String fUnitName) {
        this.fUnitName = fUnitName;
    }

    public void setfAgenIdCode(String fAgenIdCode) {
        this.fAgenIdCode = fAgenIdCode;
    }

    public String getfDestroyMemo() {
        return fDestroyMemo;
    }

    public void setfDestroyMemo(String fDestroyMemo) {
        this.fDestroyMemo = fDestroyMemo;
    }

    public Integer getfVersion() {
        return fVersion;
    }

    public void setfVersion(Integer fVersion) {
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

    public String getfApplyDate() {
        return fApplyDate;
    }

    public void setfApplyDate(String fApplyDate) {
        this.fApplyDate = fApplyDate;
    }

    public String getfApplyMan() {
        return fApplyMan;
    }

    public void setfApplyMan(String fApplyMan) {
        this.fApplyMan = fApplyMan;
    }

    public Boolean getfDestroyType() {
        return fDestroyType;
    }

    public void setfDestroyType(Boolean fDestroyType) {
        this.fDestroyType = fDestroyType;
    }

    public int getfStatus() {
        return fStatus;
    }

    public void setfStatus(int fStatus) {
        this.fStatus = fStatus;
    }
}
