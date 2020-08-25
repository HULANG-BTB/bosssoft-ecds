package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
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
    /**
     * 批量插入工作
     *
     * @param entityList
     * @return boolean
     */
    boolean insertBatch(@Param("items") List<Object> entityList);

    /**
     * 收集 可用票据信息
     *
     * @return List<BillAvailableInfoDTO>
     */
    List<BillAvailableInfoDTO> collectBillAvailableInfo();
}
