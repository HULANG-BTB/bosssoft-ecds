package com.boss.msg.entity.vo;


import lombok.Data;
import lombok.ToString;

/**
 * @author zhangxiaohui
 */
@Data
@ToString
public class SmsQueryVo {

    /**
     * SMSid
     */
    private Long id;


    /**
     * sms接收人
     */
    private String smsTo;

    /**
     * 状态
     * true为发送成功，false为发送失败
     */
    private Boolean isSent;

    /**
     * 每页记录数
     */
    private Long limit;

    /**
     * 当前页码
     */
    private Long page;


}
