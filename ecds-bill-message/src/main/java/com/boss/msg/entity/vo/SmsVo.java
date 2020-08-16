package com.boss.msg.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangxiaohui
 */
@Data
public class SmsVo{
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
     * 短信
     * 内容
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
     * 报错信息
     */
    private String error;
}
