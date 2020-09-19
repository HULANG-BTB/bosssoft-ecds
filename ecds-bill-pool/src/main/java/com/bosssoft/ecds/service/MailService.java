package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ErrorMailDto;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/24 19:08
 */
public interface MailService {

    /**
     * 发送邮件给紧急联系人
     *
     * @param list 积极联系人邮箱，邮件主题，邮件内容
     */
    void sendTemplateMail(List<ErrorMailDto> list);
}
