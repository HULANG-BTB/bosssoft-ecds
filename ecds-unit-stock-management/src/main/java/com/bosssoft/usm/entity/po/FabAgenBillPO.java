package com.bosssoft.usm.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * fab_agen_bill
 * @author 张东海
 * @version 1.0.0 2020-08-10
 */
@Data
@TableName("fab_agen_bill")
public class FabAgenBillPO {

    /**主键**/
    @TableId(type = IdType.AUTO)
    @JsonProperty
    private Long fId;

    /** 票据编码 */
    @TableField
    @JsonProperty
    private String fBillCode;

    /** 票据代码 */
    @TableField
    @JsonProperty
    private String fBillPrecode;

    /** 票据ID */
    @TableField
    private String fBillId;

    /** 仓库ID */
    @TableField
    @JsonProperty
    private Long fWarehouseId;

    /** 票据名称 */
    @TableField
    @JsonProperty
    private String fBillName;

    /** 生效日期（默认为第二天） */
    @TableField
    @JsonProperty
    private LocalDateTime fEffDate;

    /** 失效日期（默认为1年） */
    @TableField
    @JsonProperty
    private LocalDateTime fExpDate;

    /** 经办人ID */
    @TableField
    @JsonProperty
    private Long fOperId;

    /** 经办人姓名 */
    @TableField
    @JsonProperty
    private String fOperator;

    /** 经办日期 */
    @TableField
    @JsonProperty
    private LocalDateTime fOpeDate;

    /** 是否退票 */
    @TableField
    @JsonProperty
    private Integer fIsReturn;

    /** 创建时间 */
    @TableField
    @JsonProperty
    private LocalDateTime fCreateTime;

    /** 最后修改时间 */
    @TableField
    @JsonProperty
    private LocalDateTime fUpdateTime;

    /** 版本号（乐观锁） */
    @Version
    @JsonProperty
    private Integer fVersion;

    /** 区划编码 */
    @TableField
    @JsonProperty
    private String fRgnCode;

    /** 核销状态（0未核销1需要核销2不需核销） */
    @TableField
    @JsonProperty
    private Integer fHxStatus;

    /** 逻辑删除 */
    @TableField
    @JsonProperty
    private Boolean fLogicDelete;
    
}