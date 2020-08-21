package com.bosssoft.ecds.msg.entity.po;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName(value = "fbe_stock_mail")

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

    /**
     * 创建时间
     */
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 经办人id
     */
    @TableField(value = "f_oper_id", fill = FieldFill.INSERT_UPDATE)
    private Integer operatorId;

    /**
     * 经办人
     */

    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;
    /**
     * 乐观锁
     */
    @Version
    @TableField(value = "f_version",fill = FieldFill.INSERT)
    private Integer version;

    public static final String COL_F_MAIL_ID = "f_mail_id";

    public static final String COL_F_MAIL_FROM = "f_mail_from";

    public static final String COL_F_MAIL_TO = "f_mail_to";

    public static final String COL_F_MAIL_SUBJECT = "f_mail_subject";

    public static final String COL_F_MAIL_CONTENT = "f_mail_content";

    public static final String COL_F_MAIL_SENTDATE = "f_mail_sentdate";

    public static final String COL_F_MAIL_STATUS = "f_mail_is_sent";

    public static final String COL_F_MAIL_ERROR = "f_mail_error";

    public static final String COL_F_CREATE_TIME = "f_create_time";

    public static final String COL_F_UPDATE_TIME = "f_update_time";

    public static final String COL_F_OPERATOR_ID = "f_oper_id";

    public static final String COL_F_OPERATOR = "f_operator";

    public static final String COL_F_VERSION = "f_version";
}