package com.bosssoft.ecds.msg.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author pengqing
 * sendSmsVo、sendMailVo中content的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 票据类型（非税电子票据）
     */
    private String fBillType;

    /**
     * 票据图片地址
     */
    private String fBillImgUrl;

}
