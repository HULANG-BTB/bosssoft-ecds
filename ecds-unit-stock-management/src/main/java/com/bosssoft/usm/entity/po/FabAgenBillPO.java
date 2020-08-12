package com.bosssoft.usm.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * fab_agen_bill
 * @author 张东海
 * @version 1.0.0 2020-08-10
 */
@Setter
@TableName("fab_agen_bill")
public class FabAgenBillPO {

    /**主键**/
    @TableId(type = IdType.AUTO)
    private BigInteger fId;

    /** 票据编码 */
    @TableField
    private String fBillCode;

    /** 票据代码 */
    @TableField
    private String fBillPrecode;

    /** 票据ID */
    @TableField
    private String fBillId;

    /** 仓库ID */
    @TableField
    private BigInteger fWarehouseId;

    /** 票据名称 */
    @TableField
    private String fBillName;

    /** 生效日期（默认为第二天） */
    @TableField
    private LocalDateTime fEffDate;

    /** 失效日期（默认为1年） */
    @TableField
    private LocalDateTime fExpDate;

    /** 经办人ID */
    @TableField
    private BigInteger fOperId;

    /** 经办人姓名 */
    @TableField
    private String fOperator;

    /** 经办日期 */
    @TableField
    private LocalDateTime fOpeDate;

    /** 是否退票 */
    @TableField
    private Integer fIsReturn;

    /** 创建时间 */
    @TableField
    private LocalDateTime fCreateTime;

    /** 最后修改时间 */
    @TableField
    private LocalDateTime fUpdateTime;

    /** 版本号（乐观锁） */
    @Version
    private Integer fVersion;

    /** 区划编码 */
    @TableField
    private String fRgnCode;

    /** 核销状态（0未核销1需要核销2不需核销） */
    @TableField
    private Integer fHxStatus;

    /** 逻辑删除 */
    @TableField
    private Boolean fLogicDelete;

    public BigInteger getfId() {
        return fId;
    }

    public String getfBillCode() {
        return fBillCode;
    }

    public String getfBillPrecode() {
        return fBillPrecode;
    }

    public String getfBillId() {
        return fBillId;
    }

    public BigInteger getfWarehouseId() {
        return fWarehouseId;
    }

    public String getfBillName() {
        return fBillName;
    }

    public LocalDateTime getfEffDate() {
        return fEffDate;
    }

    public LocalDateTime getfExpDate() {
        return fExpDate;
    }

    public BigInteger getfOperId() {
        return fOperId;
    }

    public String getfOperator() {
        return fOperator;
    }

    public LocalDateTime getfOpeDate() {
        return fOpeDate;
    }

    public Integer getfIsReturn() {
        return fIsReturn;
    }

    public LocalDateTime getfCreateTime() {
        return fCreateTime;
    }

    public LocalDateTime getfUpdateTime() {
        return fUpdateTime;
    }

    public Integer getfVersion() {
        return fVersion;
    }

    public String getfRgnCode() {
        return fRgnCode;
    }

    public Integer getfHxStatus() {
        return fHxStatus;
    }

    public Boolean getfLogicDelete() {
        return fLogicDelete;
    }
}