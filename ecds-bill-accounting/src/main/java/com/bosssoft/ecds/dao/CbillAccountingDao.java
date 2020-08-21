package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * 通过票据校验码查询入账状态
     * PS:(单独写出来便于进行后续的各类入账状态检测)
     *
     * @return 入账状态
     */
    @Select("select f_account_status from fne_cbill_accounting where f_bill_serial_id = #{checkCode} and f_logic_delete = 0 limit 1")
    Boolean selectStatus(@Param("checkCode") String checkCode);

}
