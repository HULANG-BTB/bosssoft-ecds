package com.boss.msg.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangxiaohui
 */
@Data
public class SmsDto {
    /**
     * 短信id
     */
    @JsonSerialize(using=ToStringSerializer.class)
    private Long id;

    /**
     * 校验码
     */
    private String verifyCode;

    /**
     * 短信发送人
     */
    private String smsFrom;

    /**
     * 短信接收人
     */
    private String smsTo;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 发送时间
     */
    @JsonSerialize(using=ToStringSerializer.class)
    private Date sentDate;

    /**
     * 状态
     */
    private Boolean isSent;



    /**
     * 报错信息
     */
    private String error;
}
