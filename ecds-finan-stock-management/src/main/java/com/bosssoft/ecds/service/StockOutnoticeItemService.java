package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-13
 */
public interface StockOutnoticeItemService extends IService<StockOutnoticeItemPo> {

    /**
     * 通过pid获取出库明细
     * @param pid 出库表id
     * @return 明细list
     */
    public List<StockOutItemDto> queryItemByPid(Long pid);

}
