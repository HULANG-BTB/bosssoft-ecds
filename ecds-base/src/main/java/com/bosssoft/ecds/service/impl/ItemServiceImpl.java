package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, ItemPO> implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public int save(ItemDTO itemDTO) {
        ItemPO itemPO = BeanUtil.copyProperties(itemDTO, ItemPO.class);
        return itemDao.insert(itemPO);
    }

    @Override
    public int update(ItemDTO itemDTO) {
        ItemPO itemPO = BeanUtil.copyProperties(itemDTO, ItemPO.class);
        return itemDao.updateById(itemPO);
    }

    @Override
    public int delete(ItemDTO itemDTO) {
        return itemDao.deleteById(itemDTO.getId());
    }

    @Override
    public PageVO listByPage(PageDTO<ItemDTO> pageDTO) {
        Page<ItemPO> itemDTOPage = new Page<>();
        // 设置分页信息
        itemDTOPage.setCurrent(pageDTO.getPage());
        itemDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ItemPO.F_ID, pageDTO.getKeyword()).or().like(ItemPO.F_ITEM_NAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(ItemPO.F_CREATE_TIME);
        // 读取分页数据
        Page<ItemPO> itemPOPage = super.page(itemDTOPage, queryWrapper);
        List<ItemPO> records = itemPOPage.getRecords();
        // 转换数据
        List<ItemDTO> itemPOS = MyBeanUtil.copyListProperties(records, ItemDTO::new);
        pageDTO.setTotal(itemPOPage.getTotal());
        pageDTO.setItems(itemPOS);
        return BeanUtil.copyProperties(pageDTO, PageVO.class);
    }
}
