package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ArchiveOverViewDto;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;

import java.util.List;

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

    /**
     *  财政端查询  查询所有单位的信息，以及票据使用情况。
     * @return List<ArchiveOverViewDto>
     */
    List<ArchiveOverViewDto> queryOverViewArchiveAllInfo();
}
