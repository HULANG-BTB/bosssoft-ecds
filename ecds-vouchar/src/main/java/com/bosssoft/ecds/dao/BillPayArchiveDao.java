package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.BillPayDTO;
import com.bosssoft.ecds.entity.po.BillPayArchivePO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 票据缴销  importance
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillPayArchiveDao extends BaseMapper<BillPayArchivePO> {
    /**
     *  查询缴款信息
     * @return
     */
    List<BillPayDTO> queryBillPayInfos();

    /**
     *  根据单位编码查询所有让用户缴费票据的数量 （已归档状态下）
     *
     * @param agenCode
     * @return 票据申请数量
     */
    Long queryBillUseNumber(String agenCode);

    /**
     *  根据单位编码查询所有让用户缴费票据的数量  （已归档状态下）
     *  未审验的数量根据  总数量 - 已审核的数量
     * @param agenCode
     * @return 票据申请数量
     */
    Long queryBillAuthNumber(String agenCode);

}
