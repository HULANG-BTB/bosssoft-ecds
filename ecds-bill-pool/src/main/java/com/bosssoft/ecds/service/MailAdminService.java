package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.MailDto;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/25 9:23
 */
public interface MailAdminService {

    /**
     * 创建一个新紧急联系人
     *
     * @param mailDto 姓名，邮箱等信息
     * @return
     */
    int createMail(MailDto mailDto);

    /**
     * 更新一个紧急联系人
     *
     * @param mailDto 姓名，邮箱等信息
     * @return
     */
    int updateMail(MailDto mailDto);

    /**
     * 删除一个紧急联系人
     *
     * @param mailDto 姓名，邮箱等信息
     * @return
     */
    int deleteMail(MailDto mailDto);

    /**
     * 获取所有紧急联系人信息
     *
     * @return
     */
    List<MailDto> retrieveMailList();
}
