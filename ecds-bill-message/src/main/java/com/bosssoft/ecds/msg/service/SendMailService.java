package com.bosssoft.ecds.msg.service;

import com.bosssoft.ecds.msg.entity.dto.MailDto;

import java.util.concurrent.Future;

/**
 * @author zhangxiaohui
 */
public interface SendMailService {
   /**
    * 发送邮件的接口
    * @param mailDto 发送邮件所需信息
    * @return 返回邮件发送后状态
    */
   Future<MailDto> sendMail(MailDto mailDto);
}
