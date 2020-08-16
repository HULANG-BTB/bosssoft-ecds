package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.ItemArchivePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *   可用项目信息 Mapper 接口
 *
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface ItemArchiveDao extends BaseMapper<ItemArchivePO> {
    /**
     * 查询出单位的可用项目信息   （已归档状态下）
     * 财政端可传空值查询出的所有单位的可用信息
     *
     * @param agenName
     * @return
     */
    List<ItemArchivePO> queryItemAvailableInfo(@Param("agenName") String agenName);

    /**
     * 获取可用票据的归档信息
     *
     * @return List<ItemAvailableDTO>
     */
    List<ItemArchivePO> collectItemAvailableInfo();


}
