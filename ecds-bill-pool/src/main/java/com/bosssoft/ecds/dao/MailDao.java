package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.po.MailPo;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/24 19:44
 */
public interface MailDao {

    /**
     * 创建一个新紧急联系人
     *
     * @param mailPo 姓名，邮箱等信息
     * @return
     */
    int createMail(MailPo mailPo);

    /**
     * 更新一个紧急联系人
     *
     * @param mailPo 姓名，邮箱等信息
     * @return
     */
    int updateMail(MailPo mailPo);

    /**
     * 删除一个紧急联系人
     *
     * @param mailPo 姓名，邮箱等信息
     * @return
     */
    int deleteMail(MailPo mailPo);

    /**
     * 获取所有紧急联系人信息
     *
     * @return
     */
    List<MailPo> retrieveMailList();
}
