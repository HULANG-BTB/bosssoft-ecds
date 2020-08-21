package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 开票总览
 */
@Data
public class WriteOffInvoceDetailDTO {
    // 票据编码
    private String fBillCode;

    // 票据名称
    private String fBillName;

    // 份数
    private Integer fNumber;

    // 开票份数
//    private Integer fInvNum;

    // 开票金额
    private BigDecimal fAmt;

}
