package com.bosssoft.ecds.msg.entity.vo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 */
@Data
@ToString
public class SmsQueryVo implements Serializable {

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
     * 日期
     */
    private List<Date> period;


    /**
     * 每页记录数
     */
    private Long limit;

    /**
     * 当前页码
     */
    private Long page;


}
