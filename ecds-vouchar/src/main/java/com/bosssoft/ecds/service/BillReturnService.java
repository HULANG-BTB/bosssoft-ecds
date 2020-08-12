package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.BillReturnDto;
import com.bosssoft.ecds.entity.po.BillReturnPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 *  归档退票
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
public interface BillReturnService extends IService<BillReturnPO> {
    /**
     * 根据单位编码，获取单位退票的信息
     * @param agenIdCode
     * @return
     */
    List<BillReturnDto> queryBillReturnInfo(String agenIdCode);
}
