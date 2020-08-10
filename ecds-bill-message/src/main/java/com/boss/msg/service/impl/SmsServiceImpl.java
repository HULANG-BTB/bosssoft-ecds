package com.boss.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.boss.msg.constant.SmsConstants;
import com.boss.msg.entity.dto.SmsDto;
import com.boss.msg.entity.dto.VerifyCode;
import com.boss.msg.service.SmsService;
import com.boss.msg.util.SmsUtil;
import com.boss.msg.util.SnowflakeUtil;
import com.boss.msg.util.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author zhangxiaohui
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    /**
     * 签名ID
     */
    @Value("${sms.accessKeyID}")
    private String accessKeyId;
    /**
     * 签名密钥
     */
    @Value("${sms.accessKeySecret}")
    private String accessKeySecret;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 开票短信通知
     * 生成6位校验码，校验码由数字和小写字母组成
     * 以 用户首字母小写 + "_" + 校验码为Key，票据信息的Json格式作为Value，存入redis
     * 每一个请求结束后，不论成功与否，均存入数据库中
     *
     * @return 开票成功与否
     */
    @Override
    public boolean sendSms(SmsDto smsDto) throws ClientException {
        smsDto.setId(SnowflakeUtil.genId());
        smsDto.setSentDate(new Date());
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IAcsClient acsClient = SmsUtil.getSmsClient(accessKeyId, accessKeySecret);
        // 生成6位的校验码，由数字和小写字母组成
        String verifyCode = VerifyCodeUtil.generateVerifyCode(6, null).toLowerCase();
        smsDto.setVerifyCode(verifyCode);
        VerifyCode code = new VerifyCode(verifyCode);
        SendSmsRequest request = SmsUtil.getSmsRequest(
                smsDto.getSmsTo(),
                "ABC商城",
                "SMS_199220359", code);

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        log.info(sendSmsResponse.toString());
        if (sendSmsResponse.getCode() != null && SmsConstants.RES_SUCCESS_STATUS.equals(sendSmsResponse.getCode())) {
            // 请求发送成功
            log.info(sendSmsResponse.getCode());
            save(smsDto, true);
            return true;
        }
        // 请求发送失败
        String error = "ResponseCode:" + sendSmsResponse.getCode() +
                ",ResponseMessage:" + sendSmsResponse.getMessage() +
                ",ResponseRequestId:" + sendSmsResponse.getRequestId();
        smsDto.setError(error);
        save(smsDto, false);
        return false;
    }

    public void save(SmsDto smsDto, boolean status) {
        String value = JSON.toJSONString(smsDto);
        StringBuilder key;
        // 存入数据库
        // 存入数据库中的key格式为 status_tel_verifyCode
        if (status) {
            // 发送成功的key前缀为SUCCESS
            smsDto.setIsSent(true);
            key = new StringBuilder("SUCCESS");
        } else {
            // 发送成功的key前缀为FAIL
            smsDto.setIsSent(false);
            key = new StringBuilder("FAIL");
        }
        key.append("_").append(smsDto.getSmsTo()).append("_").append(smsDto.getVerifyCode());
        redisTemplate.opsForValue().set(key.toString(), value, 12 * 60 * 60L, TimeUnit.SECONDS);

    }
}
