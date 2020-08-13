package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.BillAvailableArchivePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuke
 * @since 2020-08-13
 */
public interface BillAvailableArchiveDao extends BaseMapper<BillAvailableArchivePO> {
    boolean insertBatch(@Param("items") List entityList);
}
