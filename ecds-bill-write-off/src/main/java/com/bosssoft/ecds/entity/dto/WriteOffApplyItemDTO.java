package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hujierong
 * @date 2020-8-17
 */
@Data
public class WriteOffApplyItemDTO {
    /**
     * 票据编码
     */
    private String fBillCode;

    /**
     * 票据名称
     */
    private String fBillName;

    /**
     * 票据批次号
     */
    private String fBatchNo;

    /**
     * 开票份数
     */
    private Integer fNumber;

    /**
     * 票据起始号
     */
    private String fBillNo1;

    /**
     * 票据结束号
     */
    private String fBillNo2;

    /**
     * 票面金额
     */
    private BigDecimal fAmt;
}
