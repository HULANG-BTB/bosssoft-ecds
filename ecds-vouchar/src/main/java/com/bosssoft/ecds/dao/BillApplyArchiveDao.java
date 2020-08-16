package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;

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

}
