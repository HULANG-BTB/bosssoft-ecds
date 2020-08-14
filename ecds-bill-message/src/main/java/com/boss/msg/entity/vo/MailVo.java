package com.boss.msg.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 */
@Data
@ToString

public class MailVo{

    /**
     * 邮件id
     */
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
