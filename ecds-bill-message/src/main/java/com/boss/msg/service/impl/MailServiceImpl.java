package com.boss.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boss.msg.entity.dto.MailDto;
import com.boss.msg.entity.po.MailPo;
import com.boss.msg.entity.vo.MailQueryVo;
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
 * todo 根据日期区间查询，前端统计图表
 */
@Service
@Slf4j
public class MailServiceImpl extends ServiceImpl<MailMapper, MailPo> implements MailService {


    @Override
    public List<MailDto> listPage(MailQueryVo mailQuery, Long page, Long limit) {
        Page<MailPo> pageQuery = new Page<>(page, limit);
        QueryWrapper<MailPo> query = getQuery(mailQuery);
        Page<MailPo> mailPoPage = baseMapper.selectPage(pageQuery, query);
        List<MailPo> mailPo = mailPoPage.getRecords();
        return DozerUtils.mapList(mailPo, MailDto.class);
    }

    @Override
    public Long getTotal(MailQueryVo mailQuery) {
        QueryWrapper<MailPo> query = getQuery(mailQuery);
        return baseMapper.selectCount(query).longValue();
    }

    @Override
    public boolean updateStatus(MailDto mailDto) {
        MailPo dbMail = baseMapper.selectById(mailDto.getId());

        // 已发件的不需要修改
        boolean isSent = dbMail.getIsSent();
        if (isSent) {
            return false;
        }

        // 修改邮件的发件错误信息
        String error = dbMail.getError();
        StringBuilder msg = new StringBuilder("人工发件,操作人：test");
        if (StringUtils.isNotBlank(error)) {
            msg.append("历史记录：").append(error);
        }
        dbMail.setIsSent(true);
        dbMail.setError(msg.toString());
        dbMail.setSentDate(new Date());
        return baseMapper.updateById(dbMail) > 0;
    }


    /**
     * 获取queryWrapper
     *
     * @param mailQuery 查询对象
     * @return QueryWrapper
     */
    public QueryWrapper<MailPo> getQuery(MailQueryVo mailQuery) {
        QueryWrapper<MailPo> query = new QueryWrapper<>();
        if (mailQuery.getId() != null) {
            query.eq("f_mail_id", mailQuery.getId());
        }
        if (StringUtils.isNotBlank(mailQuery.getMailTo())) {
            // 发件人使用模糊查询
            query.like("f_mail_to", mailQuery.getMailTo());
        }
        if (mailQuery.getIsSent() != null) {
            query.eq("f_mail_is_sent", mailQuery.getIsSent());
        }


        if (mailQuery.getPeriod() != null) {
            Date startDate = mailQuery.getPeriod().get(0);
            Date endDate = mailQuery.getPeriod().get(1);
            if (startDate != null && endDate != null && endDate.compareTo(startDate) > 0) {
                query.le("f_mail_sentDate", endDate).ge("f_mail_sentDate", startDate);
            }
        }


        return query;
    }
}
