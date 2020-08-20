package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.dto.BillCheckDto;
import com.bosssoft.ecds.entity.po.BillCheckPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface BillCheckDao extends BaseMapper<BillCheckPO> {
    /**
     * 查询各单位下的票据的审验信息
     * @param agenIdCode
     * @return List<BillCheckDto>
     */
    List<BillCheckDto>  queryBillCheckInfo(String agenIdCode);
}
