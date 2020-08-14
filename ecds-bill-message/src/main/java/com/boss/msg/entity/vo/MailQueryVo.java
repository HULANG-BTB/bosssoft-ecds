package com.boss.msg.entity.vo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 * 邮件查询对象
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
