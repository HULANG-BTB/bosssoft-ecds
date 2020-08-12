package com.boss.msg.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 */
@Data
@ToString
public class MailDto {

    /**
     * 邮件id
     */
    @JsonSerialize(using=ToStringSerializer.class)
    private Long id;

    /**
     * 邮件发送人
     */
    private String mailFrom;

    /**
     * 邮件接收人，若有多个收件人，使用 , 分割
     */
    private String mailTo;

    /**
     * 邮件主题
     */
    private String subject;

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
     * true为发送成功，false为发送失败
     */
    private Boolean isSent;

    /**
     * 报错信息
     */
    private String error;

    /**
     * 邮件附件
     */
    @JsonIgnore
    private List<File> files;

}
