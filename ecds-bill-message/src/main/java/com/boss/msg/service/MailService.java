package com.boss.msg.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boss.msg.entity.dto.MailDto;
import com.boss.msg.entity.po.MailPo;

import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/10 15:52
 */
public interface MailService extends IService<MailPo> {
    List<MailDto> listPage(MailDto mailDto);
}
