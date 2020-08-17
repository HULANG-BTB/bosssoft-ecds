package com.bosssoft.ecds.msg.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.msg.entity.po.MailPo;
import com.bosssoft.ecds.msg.mapper.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhangxiaohui
 * @create 2020/8/13 15:01
 */
@Slf4j
@Component
public class MailTask {

    @Resource
    private MailMapper mailMapper;

    /**
     * 定时删除数据库中发件时间超过30天的邮件
     */
    @Async
    @Scheduled(cron = "*/5 * * * * ?")
    public void delMailTask() {
        QueryWrapper<MailPo> query = getQuery();
        mailMapper.delete(query);
        log.info("线程 >>> "+Thread.currentThread().getName() + " --- 邮件定时任务");
    }

    /**
     * 获取邮件发送时间在30天前的查询对象
     *
     * @return QueryWrapper
     */
    public QueryWrapper<MailPo> getQuery() {
        QueryWrapper<MailPo> query = new QueryWrapper<>();
        // 获取30天前的Date对象
        Date date = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000L);
        query.le("f_mail_sentDate", date);
        return query;
    }
}
