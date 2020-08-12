package com.boss.msg.entity.vo;


import lombok.Data;
import lombok.ToString;

/**
 * @author zhangxiaohui
 */
@Data
@ToString
public class MailQueryVo {

    /**
     * 邮件id
     */
    private Long id;


    /**
     * 邮件接收人，若有多个收件人，使用 , 分割
     */
    private String mailTo;

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
