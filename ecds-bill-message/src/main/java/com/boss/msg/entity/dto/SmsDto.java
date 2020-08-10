package com.boss.msg.entity.dto;

import com.boss.msg.entity.PageRequest;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangxiaohui
 */
@Data
public class SmsDto extends PageRequest {
    /**
     * 短信id
     */
    private Long id;

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
    private Date sentDate;

    /**
     * 状态
     */
    private Boolean isSent;

    /**
     * 校验码
     */
    private String verifyCode;

    /**
     * 报错信息
     */
    private String error;
}
