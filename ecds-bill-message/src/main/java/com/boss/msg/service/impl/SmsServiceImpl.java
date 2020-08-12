package com.boss.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boss.msg.entity.dto.SmsDto;
import com.boss.msg.entity.po.SmsPo;
import com.boss.msg.mapper.SmsMapper;
import com.boss.msg.service.SmsService;
import com.boss.msg.util.DozerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxiaohui
 * @create 2020/8/12 10:31
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, SmsPo> implements SmsService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 用户通过电话号码及校验码查询票据信息
     * 先从redis中查找，若找不到则从mysql中查找
     * 返回从mysql数据库中的查找结果
     * 若不为空，则存入redis中，设置5分钟超时时间
     * @param tel        11位电话号码
     * @param verifyCode 6位校验码
     * @return 结果的json对象
     */
    @Override
    public String getBillByKey(String tel, String verifyCode) {
        // 先从redis中查询记录
        String key = "sms_" + tel + "_" + verifyCode;
        String content = (String) redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(content)) {
            // redis中非空，从redis找到了content
            return content;
        }

        // redis 中找不到，则从mysql数据库中找
        QueryWrapper<SmsPo> query = new QueryWrapper<>();
        query.eq("f_sms_to", tel)
                .eq("f_sms_verify_code", verifyCode)
                .eq("f_sms_is_sent", 1);
        SmsPo smsPo = baseMapper.selectOne(query);
        if (smsPo != null) {
            // mysql中查到了该记录，缓存进redis,设置超时时间5分钟
            redisTemplate.opsForValue().set(key, smsPo.getContent(), 5, TimeUnit.MINUTES);
            return smsPo.getContent();
        }
        return null;

    }

    /**
     * 根据短信查询对象分页查找匹配记录
     * @param smsDto 查询对象
     * @param page 当前页面
     * @param limit 当前页面大小
     * @return 匹配的发信记录
     */
    @Override
    public List<SmsDto> listPage(SmsDto smsDto, Long page, Long limit) {
        Page<SmsPo> pageQuery = new Page<>(page, limit);
        QueryWrapper<SmsPo> query = getQuery(smsDto);
        Page<SmsPo> smsPoPage = baseMapper.selectPage(pageQuery, query);
        List<SmsPo> smsPo = smsPoPage.getRecords();
        return DozerUtils.mapList(smsPo, SmsDto.class);
    }

    /**
     * 获取匹配的发信记录数
     * @param smsDto 查询对象
     * @return 发信记录数
     */
    @Override
    public Long getTotal(SmsDto smsDto) {
        QueryWrapper<SmsPo> query = getQuery(smsDto);
        return baseMapper.selectCount(query).longValue();
    }

    /**
     * 修改发信状态
     * @param smsDto 短信信息
     * @return 修改成功与否
     */
    @Override
    public boolean updateStatus(SmsDto smsDto) {
        SmsPo dbSms = baseMapper.selectById(smsDto.getId());
        // 短信发送成功的不需要修改
        boolean isSent = dbSms.getIsSent();
        if (isSent) {
            return false;
        }
        // 修改短信发送失败的记录
        String error = dbSms.getError();
        String msg = "人工发件,操作人：test";
        if (StringUtils.isNotBlank(error)) {
            msg = msg + "历史记录：" + error;
        }
        dbSms.setIsSent(true);
        dbSms.setError(msg);
        dbSms.setSentDate(new Date());
        return baseMapper.updateById(dbSms) > 0;
    }

    /**
     * 获取queryWrapper
     * @param smsDto 查询对象
     * @return QueryWrapper
     */
    public QueryWrapper<SmsPo> getQuery(SmsDto smsDto) {
        QueryWrapper<SmsPo> query = new QueryWrapper<>();
        if (smsDto.getId() != null) {
            query.eq("f_sms_id", smsDto.getId());
        }
        if (StringUtils.isNotBlank(smsDto.getSmsTo())) {
            query.eq("f_sms_to", smsDto.getSmsTo());
        }
        if (smsDto.getIsSent() != null) {
            query.eq("f_sms_is_sent", smsDto.getIsSent());
        }
        return query;
    }
}
