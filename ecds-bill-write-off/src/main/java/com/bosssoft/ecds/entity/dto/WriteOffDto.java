package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import java.util.List;

@Data
public class WriteOffDto {
    /**
     * 票据编码
     */
    private String fBillId;

    /**
     * 票据批次号
     */
    private String fBillBatchCode;

    /**
     * 票据号码
     */
    private String fBillNo;

    /**
     * 开票金额
     */
    private double fTotalAmt;

    /***
     * 项目明细
     */
    private List<UneCbillItem> uneCbillItems;
}
