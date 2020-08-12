package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.WarehousePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  仓库service
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
public interface WarehouseService extends IService<WarehousePo> {
    /**
     * 获取全部仓库信息
     */
    public List<WarehousePo> getAll();
    /**
     * 根据id获取仓库
     */
    public WarehousePo getOneById(Long warehouseId);

}
