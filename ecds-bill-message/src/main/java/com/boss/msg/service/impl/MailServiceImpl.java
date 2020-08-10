package com.boss.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boss.msg.entity.dto.MailDto;
import com.boss.msg.entity.po.MailPo;
import com.boss.msg.mapper.MailMapper;
import com.boss.msg.service.MailService;
import com.boss.msg.util.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/10 15:59
 */
@Service
@Slf4j
public class MailServiceImpl extends ServiceImpl<MailMapper, MailPo> implements MailService {


    @Override
    public List<MailDto> listPage(MailDto mailDto) {
        Page<MailPo> page = new Page<>(mailDto.getCurrent(), mailDto.getSize());
        QueryWrapper<MailPo> query = new QueryWrapper<>();
        if (mailDto.getId() != null && mailDto.getId() != 0) {
            query.eq("f_mail_id", mailDto.getId());
        }
        if (StringUtils.isNotBlank(mailDto.getMailTo())) {
            query.eq("f_mail_to", mailDto.getMailTo());
        }
        if (mailDto.getIsSent() != null) {
            query.eq("f_mail_is_sent",mailDto.getIsSent());
        }
        Page<MailPo> mailPoPage = baseMapper.selectPage(page, query);
        List<MailPo> mailPo = mailPoPage.getRecords();
        return DozerUtils.mapList(mailPo, MailDto.class);
    }
}
