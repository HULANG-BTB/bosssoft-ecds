package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.po.WarehousePo;
import com.bosssoft.ecds.mapper.WarehouseMapper;
import com.bosssoft.ecds.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehousePo> implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 获取全部仓库
     * @return
     */
    @Override
    public List<WarehousePo> getAll() {
        return warehouseMapper.selectList(null);
    }

    /**
     * 根据id获取仓库
     * @param warehouseId
     * @return
     */
    @Override
    public WarehousePo getOneById(Long warehouseId) {
        QueryWrapper<WarehousePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_warehouse_id", warehouseId);
        return warehouseMapper.selectOne(queryWrapper);
    }
}
