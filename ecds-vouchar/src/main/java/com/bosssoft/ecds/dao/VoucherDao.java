package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.OverViewArchiveDto;
import com.bosssoft.ecds.entity.po.VoucherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.query.OverViewArchiveQuery;

/**
 * <p>
 *  Mapper 接口
 *  归档总览
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface VoucherDao extends BaseMapper<VoucherPO> {
    /**
     * 根据传来的查询信息，查询出归档总览表中信息  （已归档状态下）
     * @param overViewArchiveQuery
     * @return OverViewArchiveDto
     */
    OverViewArchiveDto queryOverViewArchiveInfo(OverViewArchiveQuery overViewArchiveQuery);


}
