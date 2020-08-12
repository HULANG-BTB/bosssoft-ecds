package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ArchiveOverViewDto;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;

/**
 * <p>
 *  服务类
 *  归档总览
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface ArchiveOverViewService extends IService<ArchivePO> {
    /**
     * 根据传来的查询信息，查询出归档总览表中信息  （已归档状态下）
     * @param archiveOverViewQuery
     * @return OverViewArchiveDto
     */
    ArchiveOverViewDto queryOverViewArchiveInfo(ArchiveOverViewQuery archiveOverViewQuery);
}
