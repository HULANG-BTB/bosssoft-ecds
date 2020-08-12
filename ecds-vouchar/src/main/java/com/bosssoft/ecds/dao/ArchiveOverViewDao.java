package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.ArchiveOverViewDto;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;

/**
 * <p>
 *  Mapper 接口
 *  归档总览
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface ArchiveOverViewDao extends BaseMapper<ArchivePO> {
    /**
     * 根据传来的查询信息，查询出归档总览表中信息  （已归档状态下）
     * @param archiveOverViewQuery
     * @return OverViewArchiveDto
     */
    ArchiveOverViewDto queryOverViewArchiveInfo(ArchiveOverViewQuery archiveOverViewQuery);

}
