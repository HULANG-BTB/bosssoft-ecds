package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.ItemAvailableDTO;
import com.bosssoft.ecds.entity.po.ItemArchivePO;

import java.util.List;

/**
 * <p>
 * 服务类
 * 归档可用
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface ItemArchiveService extends IService<ItemArchivePO> {

    /**
     * 获取所有单位的可用项目情况
     * 分页查询
     *
     * @param agenCode
     * @return List<ItemAvailableDto>
     */
    List<ItemAvailableDTO> getItemAvailableInfos(String agenCode);

    /**
     * 财政端 可用项目归档
     */
    void finaItemAvailableArchive();

}
