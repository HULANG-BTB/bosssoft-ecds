package com.bosssoft.ecds.system.service;

import com.bosssoft.ecds.system.entity.dto.AlarmMessageDto;

import java.util.List;

/**
 * @description: 告警服务
 * @author: lin.wanning
 * @date: Created in 2020/8/21 14:05
 * @modified By:
 */
public interface WarnService {
    public void send(List<AlarmMessageDto> list);
}
