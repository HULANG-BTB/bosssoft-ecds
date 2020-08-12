package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.BillApplyDto;
import com.bosssoft.ecds.entity.po.CrtInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface CrtInfoDao extends BaseMapper<CrtInfoPO> {
    /**
     *  根据单位编码查询申请数量  （已归档状态下）
     *
     * @param agenIdCode
     * @return 票据申请数量
     */
    Long queryBillApplyNumber(String agenIdCode);

    /**
     * 查询单位申领票据的详细信息
     * @param agenIdCode
     * @return
     */
    List<BillApplyDto> queryBillApply(String agenIdCode);
}
