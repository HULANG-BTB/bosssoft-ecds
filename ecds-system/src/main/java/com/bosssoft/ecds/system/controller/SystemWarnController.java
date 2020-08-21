package com.bosssoft.ecds.system.controller;

import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.system.entity.dto.AlarmMessageDto;
import lombok.extern.slf4j.Slf4j;
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
    @RequestMapping("/alarm")
    public ResponseResult test(@RequestBody List<AlarmMessageDto> alarmMessageList) {
        log.info("收到告警");
        log.info(alarmMessageList.toString());
        return ResponseResult.SUCCESS();
    }
}
