package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
