package com.bosssoft.ecds.system.service.impl;

import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.system.entity.dto.AlarmMessageDto;
import com.bosssoft.ecds.system.entity.vo.SendMailVo;
import com.bosssoft.ecds.system.service.WarnService;
import com.bosssoft.ecds.system.service.client.MailClient;
import com.bosssoft.ecds.system.util.FreeMarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 告警服务
 * @author: lin.wanning
 * @date: Created in 2020/8/21 14:06
 * @modified By:
 */
@Service
@Slf4j
public class WarnServiceImpl implements WarnService {
    private final static String templateName = "systemWarnTemplate.ftl";
    @Value("${mailTo}")
    private String mailTo;
    @Autowired
    private MailClient mailClient;

    /**
     * @param list
     * @return void
     * @description 邮件发送
     * @author lin.wanning
     * @date 2020/8/21
     */
    @Override
    public void send(List<AlarmMessageDto> list) {
        Map map = new HashMap();
        map.put("alarmMessageList", list);
        String template = FreeMarkerUtil.getTemplate(templateName);
        //构建参数
        SendMailVo sendMailVo = new SendMailVo();
        sendMailVo.setContent(JSONUtil.toJsonStr(map));
        sendMailVo.setMailTo(mailTo);
        sendMailVo.setSubject("微服务链路告警");
        sendMailVo.setTemplate(template);
        //调用服务
        ResponseResult responseResult = mailClient.sendMail(sendMailVo);
        if (!responseResult.isSuccess()) {
            log.error("微服务链路告警邮件发送失败");
        }
    }
}
