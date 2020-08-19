package com.boss.msg.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhangxiaohui
 * @create 2020/8/12 14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "fbe_stock_sms")
public class SmsPo {
    /**
     * SMSID
     */
    @TableId(value = "f_sms_id", type = IdType.INPUT)
    private Long id;

    /**
     * 校验码
     */
    @TableField(value = "f_sms_verify_code")
    private String verifyCode;
    /**
     * SMS发送人
     */
    @TableField(value = "f_sms_from")
    private String smsFrom;

    /**
     * SMS接收人
     */
    @TableField(value = "f_sms_to")
    private String smsTo;

    /**
     * SMS内容
     */
    @TableField(value = "f_sms_content")
    private String content;

    /**
     * 发送时间
     */
    @TableField(value = "f_sms_sentDate")
    private Date sentDate;

    /**
     * SMS发送状态
     */
    @TableField(value = "f_sms_is_sent")
    private Boolean isSent;

    /**
     * 错误信息
     */
    @TableField(value = "f_sms_error")
    private String error;

    /**
     * 创建时间
     */
    @TableField(value = "f_create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "f_update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 经办人id
     */
    @TableField(value = "f_oper_id",fill = FieldFill.INSERT_UPDATE)
    private Integer operatorId;

    /**
     * 经办人
     */
    @TableField(value = "f_operator",fill = FieldFill.INSERT_UPDATE)
    private String operator;

    public static final String COL_F_SMS_ID = "f_sms_id";

    public static final String COL_F_SMS_FROM = "f_sms_from";

    public static final String COL_F_SMS_TO = "f_sms_to";

    public static final String COL_F_SMS_CONTENT = "f_sms_content";

    public static final String COL_F_SMS_SENTDATE = "f_sms_sentDate";

    public static final String COL_F_SMS_IS_SENT = "f_sms_is_sent";

    public static final String COL_F_SMS_ERROR = "f_sms_error";

    public static final String COL_F_CREATE_TIME = "f_create_time";

    public static final String COL_F_UPDATE_TIME = "f_update_time";

    public static final String COL_F_OPER_ID = "f_oper_id";

    public static final String COL_F_OPERATOR = "f_operator";
}