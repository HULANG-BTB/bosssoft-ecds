package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

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
     * 根据传来的查询信息，查询出归档总览表中信息
     *
     * @param archiveOverViewQuery
     * @return OverViewArchiveDto
     */
    ArchiveOverViewDTO queryOverViewArchiveInfo(ArchiveOverViewQuery archiveOverViewQuery);

    /**
     * 根据传来的查询信息，查询出归档总览表中信息
     *
     * @param archiveOverViewQuery
     * @return OverViewArchiveDto
     */
    List<ArchiveOverViewDTO> queryOverViewArchiveInfos(ArchiveOverViewQuery archiveOverViewQuery);

    /**
     * 查询所有信息 以分页查询的形式 模糊查询
     *
     * @param archiveOverViewQuery
     * @return
     */
    PageDTO<ArchiveOverViewDTO> queryOverViewArchiveInfoPage(ArchiveOverViewQuery archiveOverViewQuery);

    /**
     * 批量更新归档信息
     *
     * @param list
     * @return boolean
     */
    @CacheEvict(cacheNames = "allTotal")
    @Transactional(rollbackFor = Exception.class)
    Boolean updateBatch(List<ArchiveOverViewDTO> list);
}
