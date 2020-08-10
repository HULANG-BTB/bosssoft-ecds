package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.po.ItemStdPO;
import com.bosssoft.ecds.dao.ItemStdDao;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.ItemStdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@Service
public class ItemStdServiceImpl extends ServiceImpl<ItemStdDao, ItemStdPO> implements ItemStdService {

    @Autowired
    private ItemStdDao itemStdDao;

    @Override
    public boolean save(ItemStdDTO itemStdDTO) {
        ItemStdPO itemStdPO = MyBeanUtil.myCopyProperties(itemStdDTO, ItemStdPO.class);
        return itemStdDao.insert(itemStdPO) == 1;
    }

    @Override
    public boolean update(ItemStdDTO itemStdDTO) {
        ItemStdPO itemStdPO = MyBeanUtil.myCopyProperties(itemStdDTO, ItemStdPO.class);
        return itemStdDao.updateById(itemStdPO) == 1;
    }

    @Override
    public boolean delete(ItemStdDTO itemStdDTO) {
        ItemStdPO itemStdPO = MyBeanUtil.myCopyProperties(itemStdDTO, ItemStdPO.class);
        return itemStdDao.deleteById(itemStdPO) == 1;
    }

    @Override
    public PageVO listByPage(PageDTO<ItemStdDTO> pageDTO) {
        Page<ItemStdPO> itemStdDTOPage = new Page<>();
        // 设置分页信息
        itemStdDTOPage.setCurrent(pageDTO.getPage());
        itemStdDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<ItemStdPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ItemStdPO.F_ID, pageDTO.getKeyword())
                .or()
                .like(ItemStdPO.F_ITEMSTD_NAME, pageDTO.getKeyword())
                .or()
                .like(ItemStdPO.F_ISENABLE,pageDTO.getKeyword());
        queryWrapper.orderByAsc(ItemPO.F_CREATE_TIME);
        // 读取分页数据
        Page<ItemStdPO> itemPOPage = itemStdDao.selectPage(itemStdDTOPage, queryWrapper);
        List<ItemStdPO> records = itemPOPage.getRecords();
        // 转换数据
        List<ItemStdDTO> itemPOS = MyBeanUtil.copyListProperties(records, ItemStdDTO::new);
        pageDTO.setTotal(itemPOPage.getTotal());
        pageDTO.setItems(itemPOS);
        return MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
    }

    @Override
    public boolean batchdelete(List<ItemStdDTO> itemStdDTOS) {
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<ItemStdDTO> iterator = itemStdDTOS.iterator(); iterator.hasNext(); ) {
            idList.add(iterator.next().getId());
        }
        return itemStdDao.deleteBatchIds(idList) == 1;
    }
}
