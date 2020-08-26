package com.bosssoft.ecds.msg.entity.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * @author zhangxiaohui
 */
@Data
public class SendSmsVo implements Serializable {

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


}
