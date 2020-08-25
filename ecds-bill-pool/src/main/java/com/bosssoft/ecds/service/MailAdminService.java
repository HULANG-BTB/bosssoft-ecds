package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.MailDto;
import com.bosssoft.ecds.entity.po.MailPo;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/25 9:23
 */
public interface MailAdminService {

    int createMail(MailDto mailDto);

    int updateMail(MailDto mailDto);

    int deleteMail(MailDto mailDto);

    List<MailDto> retrieveMailList();
}
