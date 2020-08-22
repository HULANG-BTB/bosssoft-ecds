package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import java.util.Date;

@Data
public class MessageDto {

    /**
     * 票据代码
     */
    private String fBillId;

    /**
     *  票据号码
     */
    private String fBillNo;

    /**
     * 开票时间
     */
    private Date fCreateTime;

    /**
     * 开票金额
     */
    private String fTotalAmt;

    /**
     * 开票单位
     */
    private String fPlaceName;

    /**
     * 缴款人
     */
    private String fPayerName;

    /**
     * 缴款人邮箱
     */
    private String fPayerEmail;

    /**
     * 缴款人号码
     */
    private String fPayerTel;

    /**
     * 校验码
     */
    private String fCheckCode;

}
