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
     * 财政端
     */

    /**
     * 获取所有单位的可用项目情况
     * 分页查询
     *
     * @return List<ItemAvailableDto>
     */
    List<ItemAvailableDTO> getItemAvailableInfos();

    /**
     * 财政端 可用项目归档
     */
    void finaItemAvailableArchive();

    /**
     * 单位端
     */

    /**
     * 根据单位编码获取可用票据归档的情况
     *
     * @param agenCode
     * @return BillApplyDto
     */
    ItemAvailableDTO getBillApplyInfo(String agenCode);

}
