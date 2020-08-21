package com.bosssoft.ecds.entity.vo;

import lombok.Data;


/**
 * @author zhangxiaohui
 */
@Data
public class SendSmsVo {

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
