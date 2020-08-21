package com.bosssoft.ecds.system.controller;

import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.system.entity.dto.AlarmMessageDto;
import com.bosssoft.ecds.system.service.WarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 微服务链路告警
 * @author: lin.wanning
 * @date: Created in 2020/8/21 9:25
 * @modified By:
 */
@RestController
@Slf4j
@RequestMapping(value = "/system")
public class SystemWarnController {
    @Autowired
    private WarnService warnService;

    @RequestMapping("/alarm")
    public ResponseResult test(@RequestBody List<AlarmMessageDto> alarmMessageList) {
        warnService.send(alarmMessageList);
        return ResponseResult.SUCCESS();
    }
}
