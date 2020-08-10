package com.bosssoft.ecds.service.impl;

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

import java.util.ArrayList;
import java.util.Iterator;
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
    public boolean save(ItemDTO itemDTO) {
        ItemPO itemPO = MyBeanUtil.myCopyProperties(itemDTO, ItemPO.class);
        return itemDao.insert(itemPO) == 1;
    }

    @Override
    public boolean update(ItemDTO itemDTO) {
        ItemPO itemPO = MyBeanUtil.myCopyProperties(itemDTO, ItemPO.class);
        return itemDao.updateById(itemPO) == 1;
    }

    @Override
    public boolean delete(ItemDTO itemDTO) {
        return itemDao.deleteById(itemDTO.getId()) == 1;
    }

    @Override
    public PageVO listByPage(PageDTO<ItemDTO> pageDTO) {
        Page<ItemPO> itemDTOPage = new Page<>();
        // 设置分页信息
        itemDTOPage.setCurrent(pageDTO.getPage());
        itemDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ItemPO.F_ID, pageDTO.getKeyword())
                .or()
                .like(ItemPO.F_ITEM_NAME, pageDTO.getKeyword())
                .or()
                .like(ItemPO.F_ISENABLE,pageDTO.getKeyword());
        queryWrapper.orderByAsc(ItemPO.F_CREATE_TIME);
        // 读取分页数据
        Page<ItemPO> itemPOPage = super.page(itemDTOPage, queryWrapper);
        List<ItemPO> records = itemPOPage.getRecords();
        // 转换数据
        List<ItemDTO> itemPOS = MyBeanUtil.copyListProperties(records, ItemDTO::new);
        pageDTO.setTotal(itemPOPage.getTotal());
        pageDTO.setItems(itemPOS);
        return MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
    }

    @Override
    public boolean batchDelete(List<ItemDTO> itemDTOS) {
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<ItemDTO> iterator = itemDTOS.iterator(); iterator.hasNext(); ) {
            idList.add(iterator.next().getId());
        }
        return itemDao.deleteBatchIds(idList) == 1;
    }

    @Override
    public boolean batchUpdate(List<ItemDTO> itemDTOS) {
        List<ItemPO> itemPOS = MyBeanUtil.copyListProperties(itemDTOS, ItemPO::new);
        return super.updateBatchById(itemPOS);
    }
}
