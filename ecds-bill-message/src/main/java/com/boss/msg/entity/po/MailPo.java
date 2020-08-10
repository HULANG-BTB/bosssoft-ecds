package com.boss.msg.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangxiaohui
 * @create 2020/8/10 14:53
 */
@Data
@ToString

@TableName(value = "f_mail")
public class MailPo implements Serializable {
    /**
     * 邮件id
     */
    @TableId(value = "f_mail_id", type = IdType.INPUT)
    private Long id;

    /**
     * 邮件发送人
     */
    @TableField(value = "f_mail_from")
    private String mailFrom;

    /**
     * 邮件接收人
     */
    @TableField(value = "f_mail_to")
    private String mailTo;

    /**
     * 邮件主题
     */
    @TableField(value = "f_mail_subject")
    private String subject;

    /**
     * 邮件内容
     */
    @TableField(value = "f_mail_content")
    private String content;

    /**
     * 发送时间
     */
    @TableField(value = "f_mail_sentdate")
    private Date sentDate;

    /**
     * 邮件发送状态
     */
    @TableField(value = "f_mail_is_sent")
    private Boolean isSent;

    /**
     * 错误信息
     */
    @TableField(value = "f_mail_error")
    private String error;

    public static final String COL_F_MAIL_ID = "f_mail_id";

    public static final String COL_F_MAIL_FROM = "f_mail_from";

    public static final String COL_F_MAIL_TO = "f_mail_to";

    public static final String COL_F_MAIL_SUBJECT = "f_mail_subject";

    public static final String COL_F_MAIL_CONTENT = "f_mail_content";

    public static final String COL_F_MAIL_SENTDATE = "f_mail_sentdate";

    public static final String COL_F_MAIL_STATUS = "f_mail_is_sent";

    public static final String COL_F_MAIL_ERROR = "f_mail_error";
}