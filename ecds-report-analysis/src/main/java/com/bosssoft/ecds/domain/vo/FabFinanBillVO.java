package com.bosssoft.ecds.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author syf
 * @Date 2020/8/15 17:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FabFinanBillVO {

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
    private Date fEffDate;

    /**
     * 失效日期
     */
    private Date fExpDate;

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
    private Date fOpeDate;

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
    private Date fCreateTime;

    /**
     * 最后修改时间
     */
    private Date fUpdateTime;

    /**
     * 区划编码
     */
    private String fRgnCode;

    /**
     * 核销状态（0未核销1需要核销2不需核销）
     */
    private Integer fHxStatus;

}
