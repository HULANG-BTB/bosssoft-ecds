package com.bosssoft.ecds.msg.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
