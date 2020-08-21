package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.BillWarnArchivePO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 *  归档预警
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillWarnArchiveDao extends BaseMapper<BillWarnArchivePO> {
    /**
     * 查询一天时间内票据的预警信息
     *
     * @return List<BillWarnDto>
     */
    List<BillWarnArchivePO> queryBillWarnInfos();
}
