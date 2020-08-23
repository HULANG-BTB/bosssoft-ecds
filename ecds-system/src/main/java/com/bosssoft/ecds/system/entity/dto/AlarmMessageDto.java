package com.bosssoft.ecds.system.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description: 链路警报实体类
 * @author: lin.wanning
 * @date: Created in 2020/8/17 13:54
 * @modified By:
 */
@Data
public class AlarmMessageDto {
    private Integer scopeId;
    private String name;
    private String id0;
    private String id1;
    private String alarmMessage;
    private Date startTime;
}
