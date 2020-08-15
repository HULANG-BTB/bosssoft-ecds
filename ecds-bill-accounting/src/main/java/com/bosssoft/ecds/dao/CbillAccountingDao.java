package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
@Mapper
public interface CbillAccountingDao extends BaseMapper<CbillAccountingPO> {

    /**
     * 通过票据校验码查询待缴金额
     *
     * @return 待缴金额
     */
    @Select("select f_wait_account from fne_cbill_accounting where f_bill_serial_id = #{billSerialId} and f_logic_delete = 0 limit 1")
    BigDecimal selectAccount(@Param("billSerialId") String billSerialId);

    /**
     * 通过票据校验码查询入账状态
     *
     * @return 入账状态
     */
    @Select("select f_account_status from fne_cbill_accounting where f_bill_serial_id = #{checkCode} and f_logic_delete = 0 limit 1")
    Boolean selectStatus(@Param("checkCode") String checkCode);

}
