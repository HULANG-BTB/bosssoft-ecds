package com.bosssoft.ecds.msg.service.impl;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.bosssoft.ecds.msg.common.constant.SmsConstants;
import com.bosssoft.ecds.msg.entity.dto.SmsDto;
import com.bosssoft.ecds.msg.entity.dto.VerifyCode;
import com.bosssoft.ecds.msg.service.SendSmsService;
import com.bosssoft.ecds.msg.util.SmsUtil;
import com.bosssoft.ecds.msg.util.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.concurrent.Future;


/**
 * @author zhangxiaohui
 */
@Slf4j
@Service
public class SendSmsServiceImpl implements SendSmsService {
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
    @Async
    @Override
    public Future<SmsDto> sendSms(SmsDto smsDto) throws ClientException {

        IAcsClient acsClient = SmsUtil.getSmsClient(accessKeyId, accessKeySecret);
        // 生成6位的校验码，由数字和小写字母组成
        VerifyCode code = getValidCode(smsDto);
        SendSmsRequest request = SmsUtil.getSmsRequest(
                smsDto.getSmsTo(),
                "ABC商城",
                "SMS_199220359", code);

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && SmsConstants.RES_SUCCESS_STATUS.equals(sendSmsResponse.getCode())) {
            // 请求发送成功
            smsDto.setIsSent(true);
            return new AsyncResult<>(smsDto);
        }
        // 请求发送失败
        String error = "发信错误：ResponseCode:" + sendSmsResponse.getCode() +
                ",ResponseMessage:" + sendSmsResponse.getMessage() +
                ",ResponseRequestId:" + sendSmsResponse.getRequestId();
        smsDto.setError(error);
        smsDto.setIsSent(false);
        return new AsyncResult<>(smsDto);

    }

    /**
     * 获取与手机号对应的6位校验码
     * 优化：雪花算法生成18位数字，转为10位的62进制字符串作为验证码
     *
     * @param smsDto 获取手机号，并设置校验码
     * @return 校验码对象
     */
    public VerifyCode getValidCode(SmsDto smsDto) {
        String verifyCode;
        Object value;

        // 6位由小写字母和数字组成的验证码由21亿种
        do {
            verifyCode = VerifyCodeUtil.generateVerifyCode(6, null).toLowerCase();
            String key = "bill_" + smsDto.getSmsTo() + "_" + verifyCode;
            value = redisTemplate.opsForValue().get(key);
        } while (value != null);

        smsDto.setVerifyCode(verifyCode);
        return new VerifyCode(verifyCode);
    }


}
