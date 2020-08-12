package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.ItemAvailableInfoDto;
import com.bosssoft.ecds.entity.po.ItemVoucherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface ItemVoucherDao extends BaseMapper<ItemVoucherPO> {
    /**
     * 查询出单位的可用项目信息   （已归档状态下）
     * 财政端可传空值查询出的所有单位的可用信息
     * @param agenIdCode
     * @return
     */
    List<ItemAvailableInfoDto> queryItemAvailableInfo(String agenIdCode);
}
