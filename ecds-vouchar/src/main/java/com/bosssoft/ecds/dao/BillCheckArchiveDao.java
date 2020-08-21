package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.BillCheckArchivePO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 *  审验记录
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillCheckArchiveDao extends BaseMapper<BillCheckArchivePO> {
    /**
     * 查询各单位下的票据的审验信息
     *
     * @param agenName
     * @return List<BillCheckDto>
     */
    List<BillCheckArchivePO> queryBillCheckInfo(@Param("agenName") String agenName);

    /**
     * 收集票据的审验信息
     *
     * @return
     */
    List<BillCheckArchivePO> collectBillCheckInfo();

    /**
     * 获取各个公司一定时间内的审核信息
     *
     * @return List<CBillAccountingPO>
     */
    List<CbillAccountingPO> collectCBillAccountInfo();

}
