package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.BillCheckDto;
import com.bosssoft.ecds.entity.po.BillCheckPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类  审验记录
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillCheckService extends IService<BillCheckPO> {
    /**
     * 查询各单位下的票据的审验信息
     * @param agenIdCode
     * @return List<BillCheckDto>
     */
    List<BillCheckDto> queryBillCheckInfo(String agenIdCode);
}
