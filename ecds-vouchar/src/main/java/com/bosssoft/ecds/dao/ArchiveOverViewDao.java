package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.po.ArchivePO;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 归档总览
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface ArchiveOverViewDao extends BaseMapper<ArchivePO> {
    /**
     * 根据传来的查询信息，查询出归档总览表中信息
     *
     * @param archiveOverViewQuery
     * @return OverViewArchiveDto
     */
    ArchiveOverViewDTO queryOverViewArchiveInfo(@Param("query") ArchiveOverViewQuery archiveOverViewQuery);


    /**
     * 查询所有单位的归档总览表中的信息
     *
     * @param archiveOverViewQuery
     * @return List<ArchiveOverViewDTO>
     */
    List<ArchiveOverViewDTO> queryOverViewArchiveAllInfo(@Param("query") ArchiveOverViewQuery archiveOverViewQuery);

}
