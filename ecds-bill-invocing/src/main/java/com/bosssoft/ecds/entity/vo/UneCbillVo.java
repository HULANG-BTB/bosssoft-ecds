package com.bosssoft.ecds.entity.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UneCbillVo {

    /**
     * 票据代码
     */
    private String fBillId;

    /**
     * 票据号码
     */
    private String fBillNo;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date fCreateTime;

    /**
     * 票据金额
     */
    private double fTotalAmt;

    /**
     * 开票单位
     */
    private String fPlaceName;

    /**
     * 开票状态
     */
    private int fState;
}
