package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
public interface StockOutnoticeService extends IService<StockOutnoticePo> {

    /**
     * 获取最新的业务单号
     *
     * @return 新业务单号
     */
    Long getNewBussNo();

}
