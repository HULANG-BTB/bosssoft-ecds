package com.bosssoft.ecds.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_finan_bill")
public class FabFinanBillPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 财政票据表_主键
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 票据编码（18位）
     */
    private String fBillCode;

    /**
     * 票据代码（8位）
     */
    private String fBillPrecode;

    /**
     * 票据ID（10）
     */
    private String fBillId;

    /**
     * 仓库ID
     */
    private Long fWarehouseId;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 单位编码
     */
    private Long fAgenCode;

    /**
     * 单位名称
     */
    private String fAgenName;

    /**
     * 生效日期
     */
    private LocalDateTime fEffDate;

    /**
     * 失效日期
     */
    private LocalDateTime fExpDate;

    /**
     * 经办人ID
     */
    private Long fOperId;

    /**
     * 经办人
     */
    private String fOperator;

    /**
     * 经办日期
     */
    private LocalDateTime fOpeDate;

    /**
     * 是否发放
     */
    private Integer fIsPutout;

    /**
     * 是否退票
     */
    private Integer fIsReturn;

    /**
     * 是否作废
     */
    private Integer fIsInvalid;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime fUpdateTime;

    /**
     * 版本号（乐观锁）
     */
    private Integer fVersion;

    /**
     * 区划编码
     */
    private String fRgnCode;

    /**
     * 核销状态（0未核销1需要核销2不需核销）
     */
    private Integer fHxStatus;

    /**
     * 逻辑删除
     */
    private Boolean fLogicDelete;


}
