package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import java.util.List;

@Data
public class WriteOffDto {
    /**
     * 票据编码
     */
    private String fbillId;

    /**
     * 票据批次号
     */
    private String fbillBatchCode;

    /**
     * 票据种类名称
     */
    private String ftype;

    /**
     * 票据号码
     */
    private String fbillNo;

    /**
     * 开票金额
     */
    private double ftotalAmt;

    /***
     * 项目明细
     */
    private List<UneCbillItem> uneCbillItems;
}
