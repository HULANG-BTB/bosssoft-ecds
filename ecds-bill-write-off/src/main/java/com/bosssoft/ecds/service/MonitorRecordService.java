package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffMonitorDetailDTO;

import java.util.List;

/**
 * 预警记录的接口
 */
public interface MonitorRecordService {

    /**
     * 生成预警
     *
     * @param writeOffDetailDTO
     * @return List
     */
    List<WriteOffMonitorDetailDTO> getMonitorDetail(WriteOffDetailDTO writeOffDetailDTO);
}
