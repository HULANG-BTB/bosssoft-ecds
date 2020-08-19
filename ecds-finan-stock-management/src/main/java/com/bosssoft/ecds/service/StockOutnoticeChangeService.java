package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.StockOutnoticeChangePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.StockOutVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
public interface StockOutnoticeChangeService extends IService<StockOutnoticeChangePo> {

    /**
     * 通过StockOutVo插入变动数据
     *
     * @param outVo outVo
     * @return 是否成功
     */
    Boolean insertByOutVo(StockOutVo outVo);

}
