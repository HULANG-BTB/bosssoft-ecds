package com.boss.msg.controller;

import com.aliyuncs.exceptions.ClientException;
import com.boss.msg.entity.dto.SmsDto;
import com.boss.msg.entity.vo.SmsVo;
import com.boss.msg.service.SmsService;
import com.boss.msg.util.DozerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangxiaohui
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Resource
    private SmsService smsService;

    @RequestMapping("/send")
    public boolean smsTest(SmsVo smsVo) throws ClientException {
        smsVo.setSmsTo("15651995937");
        return smsService.sendSms(DozerUtils.map(smsVo, SmsDto.class));
    }
}
