package com.boss.msg.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
