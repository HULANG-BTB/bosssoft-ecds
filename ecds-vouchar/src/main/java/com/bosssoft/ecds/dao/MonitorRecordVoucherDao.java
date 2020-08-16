package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.BillWarnDto;
import com.bosssoft.ecds.entity.po.MonitorRecordVoucherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface MonitorRecordVoucherDao extends BaseMapper<MonitorRecordVoucherPO> {
    /**
     *  根据单位代码查询票据的预警信息
      * @param agenIdCode
     *  @return List<BillWarnDto>
     */
    List<BillWarnDto> queryBillWarnInfo(String agenIdCode);
}
