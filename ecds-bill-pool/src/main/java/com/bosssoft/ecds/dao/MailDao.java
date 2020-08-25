package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.ErrorMailDto;
import com.bosssoft.ecds.entity.dto.MailDto;
import com.bosssoft.ecds.entity.po.MailPo;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/24 19:44
 */
public interface MailDao {

    int createMail(MailPo mailPo);

    int updateMail(MailPo mailPo);

    int deleteMail(MailPo mailPo);

    List<MailPo> retrieveMailList();
}
