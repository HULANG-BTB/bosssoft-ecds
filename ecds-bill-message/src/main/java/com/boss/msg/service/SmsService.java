package com.boss.msg.service;

import com.aliyuncs.exceptions.ClientException;
import com.boss.msg.entity.dto.SmsDto;

/**
 * @author zhangxiaohui
 */
public interface SmsService {
    /**
     * 短信发送接口
     * @param smsDto 发送短信所需的相关信息
     * @return 发送成功与否
     * @throws ClientException sms获取response对象可能发生的异常
     */
    boolean sendSms(SmsDto smsDto) throws ClientException;
}
