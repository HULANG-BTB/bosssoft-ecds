package com.boss.msg.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boss.msg.entity.po.SmsPo;
import com.boss.msg.mapper.SmsMapper;
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
public class SmsTask {

    @Resource
    private SmsMapper smsMapper;

    /**
     * 定时删除数据库中发件时间超过30天的短信
     */
    @Async
    @Scheduled(cron = "*/5 * * * * ?")
    public void delMailTask() {
        QueryWrapper<SmsPo> query = getQuery();
        smsMapper.delete(query);
        log.info(Thread.currentThread().getName() + "短信定时任务定时任务");
    }

    /**
     * 获取邮件发送时间在30天前的查询对象
     *
     * @return QueryWrapper
     */
    public QueryWrapper<SmsPo> getQuery() {
        QueryWrapper<SmsPo> query = new QueryWrapper<>();
        // 获取30天前的Date对象
        Date date = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000L);
        query.le("f_sms_sentDate", date);
        return query;
    }

}
