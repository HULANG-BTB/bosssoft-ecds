package com.bosssoft.ecds.entity.dto;

import com.bosssoft.ecds.entity.po.UneCbillItem;
import lombok.Data;

import java.math.BigDecimal;
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
     * 票据类型
     */
    private String fType;

    /**
     * 开票金额
     */
    private BigDecimal fTotalAmt;

    /***
     * 项目明细
     */
    private List<UneCbillItem> uneCbillItems;
}
