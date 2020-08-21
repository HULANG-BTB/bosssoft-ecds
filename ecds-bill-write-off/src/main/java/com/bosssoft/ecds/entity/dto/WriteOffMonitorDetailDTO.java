package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WriteOffMonitorDetailDTO {

    // 预警日期
    private String fDate;

    // 预警事项
    private String itemName;

    // 预警内容
    private String itemContent;

}
