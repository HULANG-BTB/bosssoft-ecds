package com.bosssoft.ecds.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.msg.entity.dto.MailDto;
import com.bosssoft.ecds.msg.entity.po.MailPo;
import com.bosssoft.ecds.msg.entity.vo.MailQueryVo;
import com.bosssoft.ecds.msg.exception.MsgException;
import com.bosssoft.ecds.msg.mapper.MailMapper;
import com.bosssoft.ecds.msg.service.MailService;
import com.bosssoft.ecds.msg.util.DozerUtils;
import com.bosssoft.ecds.msg.util.SnowflakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 */
@Service
@Slf4j
public class MailServiceImpl extends ServiceImpl<MailMapper, MailPo> implements MailService {

    @Resource
    private MailMapper mailMapper;

    @Override
    public List<MailDto> listPage(MailQueryVo mailQuery, Long page, Long limit) {
        Page<MailPo> pageQuery = new Page<>(page, limit);
        QueryWrapper<MailPo> query = getQuery(mailQuery);
        Page<MailPo> mailPoPage = mailMapper.selectPage(pageQuery, query);
        List<MailPo> mailPo = mailPoPage.getRecords();
        return DozerUtils.mapList(mailPo, MailDto.class);
    }

    @Override
    public Long getTotal(MailQueryVo mailQuery) {
        QueryWrapper<MailPo> query = getQuery(mailQuery);
        return mailMapper.selectCount(query).longValue();
    }

    @Override
    public boolean updateStatus(MailDto mailDto) {
        MailPo dbMail = mailMapper.selectById(mailDto.getId());

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
        return mailMapper.updateById(dbMail) > 0;
    }

    @Override
    public boolean saveMailManually(MailDto mailDto) {
        MailPo mail = DozerUtils.map(mailDto, MailPo.class);
        return mailMapper.insert(mail) > 0;
    }

    @Override
    @Async
    public void saveAutoSentMail(MailDto mailDto) {
        MailPo mail = DozerUtils.map(mailDto, MailPo.class);
        mail.setId(SnowflakeUtil.genId());
        mail.setIsSent(true);
        mail.setSentDate(new Date());
        boolean b = mailMapper.insert(mail) > 0;
        if (!b) {
            throw new MsgException("邮件保存失败");
        }
    }


    /**
     * 获取queryWrapper
     *
     * @param mailQuery 查询对象
     * @return QueryWrapper
     */
    public QueryWrapper<MailPo> getQuery(MailQueryVo mailQuery) {
        QueryWrapper<MailPo> query = new QueryWrapper<>();
        query.orderByDesc(MailPo.COL_F_MAIL_SENTDATE);
        if (mailQuery.getId() != null) {
            query.eq(MailPo.COL_F_MAIL_ID, mailQuery.getId());
        }
        if (StringUtils.isNotBlank(mailQuery.getMailTo())) {
            // 发件人使用模糊查询
            query.like(MailPo.COL_F_MAIL_TO, mailQuery.getMailTo());
        }
        if (mailQuery.getIsSent() != null) {
            query.eq(MailPo.COL_F_MAIL_STATUS, mailQuery.getIsSent());
        }
        if (mailQuery.getPeriod() != null) {
            Date startDate = mailQuery.getPeriod().get(0);
            Date endDate = mailQuery.getPeriod().get(1);
            if (startDate != null && endDate != null && endDate.compareTo(startDate) > 0) {
                query.le(MailPo.COL_F_MAIL_SENTDATE, endDate).ge(MailPo.COL_F_MAIL_SENTDATE, startDate);
            }
        }


        return query;
    }
}
