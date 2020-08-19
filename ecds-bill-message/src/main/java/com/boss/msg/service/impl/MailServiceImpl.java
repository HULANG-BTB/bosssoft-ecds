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

import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 */
@Service
@Slf4j
public class MailServiceImpl extends ServiceImpl<MailMapper, MailPo> implements MailService {


    @Override
    public List<MailDto> listPage(MailDto mailDto, Long page, Long limit) {
        Page<MailPo> pageQuery = new Page<>(page, limit);
        QueryWrapper<MailPo> query = getQuery(mailDto);
        Page<MailPo> mailPoPage = baseMapper.selectPage(pageQuery, query);
        List<MailPo> mailPo = mailPoPage.getRecords();
        return DozerUtils.mapList(mailPo, MailDto.class);
    }

    @Override
    public Long getTotal(MailDto mailDto) {
        QueryWrapper<MailPo> query = getQuery(mailDto);
        return baseMapper.selectCount(query).longValue();
    }

    @Override
    public boolean updateStatus(MailDto mailDto) {
        MailPo dbMail = baseMapper.selectById(mailDto.getId());

        // 已发件的不需要修改
        boolean isSent = dbMail.getIsSent();
        if (isSent){
            return false;
        }

        // 修改邮件的发件错误信息
        String error = dbMail.getError();
        StringBuilder msg = new StringBuilder("人工发件,操作人：test");
        if (StringUtils.isNotBlank(error)){
            msg.append("历史记录：").append(error);
        }
        dbMail.setIsSent(true);
        dbMail.setError(msg.toString());
        dbMail.setSentDate(new Date());
        return baseMapper.updateById(dbMail) > 0;
    }


    /**
     * 获取queryWrapper
     * @param mailDto 查询对象
     * @return QueryWrapper
     */
    public QueryWrapper<MailPo> getQuery(MailDto mailDto) {
        QueryWrapper<MailPo> query = new QueryWrapper<>();
        if (mailDto.getId() != null ) {
            query.eq("f_mail_id", mailDto.getId());
        }
        if (StringUtils.isNotBlank(mailDto.getMailTo())) {
            query.eq("f_mail_to", mailDto.getMailTo());
        }
        if (mailDto.getIsSent() != null) {
            query.eq("f_mail_is_sent", mailDto.getIsSent());
        }
        return query;
    }
}
