package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 开票明细
 */
@Data
public class WriteOffBillInvDetailDTO {

    // 票据批次编码
    private String fBatchNo;

    // 票据批次名称

    // 份数
    private Integer fNumber;

    // 起始号
    private String fBillNo1;

    // 终止号
    private String fBillNo2;

    // 开票金额
    private BigDecimal fAmt;
}
