package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.SourceSetDto;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public interface SourceSetService {

    /**
     * 更新票据池设置
     * @param sourceSetDto 需要修改的票据池编码、最小推送数、修改类型
     * @return
     */
    int updateMin(SourceSetDto sourceSetDto);

    /**
     * 更新票据池设置
     * @param sourceSetDto 需要修改的票据池编码、阈值、修改类型
     * @return
     */
    int updatePushNumber(SourceSetDto sourceSetDto);

    /**
     * 添加新的票据池
     * @param sourceSetDto 根据票据编码创建新的数据表，在票据池设置表和对应表中插入信息
     * @return
     */
    int createSource(SourceSetDto sourceSetDto);

    /**
     * 更新票据池设置
     * @param sourceSetDto 票据池修改的全部信息包括推送数量、阈值、修改类型等
     * @return int
     */
    int updateSet(SourceSetDto sourceSetDto);

    /**
     * 取出全部票据池设置信息
     * @return List<SourceSetDto>
     */
    List<SourceSetDto> retrieveSetList();

    /**
     * 根据票据编码取出对应票据池设置信息
     * @param billTypeCode 票据编码
     * @return
     */
    SourceSetDto retrieveSetByCode(String billTypeCode);
}
