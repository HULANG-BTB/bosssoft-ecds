package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuke
 * @since 2020-08-14
 */
public interface CbillAccountingDao extends BaseMapper<CbillAccountingPO> {
    List<CbillAccountingPO> getCBillAccPOS();
}
