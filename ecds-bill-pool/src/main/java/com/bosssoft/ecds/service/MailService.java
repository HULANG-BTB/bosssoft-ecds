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
     *
     * @param to 邮件接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendTemplateMail(List<ErrorMailDto> list);
}
