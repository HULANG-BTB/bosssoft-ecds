package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 *  归档领用
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillApplyArchiveDao extends BaseMapper<BillApplyArchivePO> {

    /**
     * 查询所有公司的申领信息
     *
     * @return List<BillApplyDTO>
     */
    List<BillApplyArchivePO> queryBillApplyAll();

    /**
     * 查询公司信息
     *
     * @param agenCode
     * @return 公司信息
     */
    ArchiveOverViewDTO queryNewAgenInfo(@Param("agenCode") String agenCode);
}
