package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.dto.ItemDto;
import com.bosssoft.ecds.entity.po.ItemPo;
import com.bosssoft.ecds.dao.ItemMapper;
import com.bosssoft.ecds.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, ItemPo> implements ItemService {
    @Autowired(required = false)
    private ItemMapper itemMapper;

    /**
     * @description: 插入票据销毁申请明细表信息，此时需将dto转为po
     * @param: [itemDtoList]
     * @return: boolean
     * @date: 2020/8/13
     */
    @Override
    public boolean insertItemInfo(List<ItemDto> itemDtoList, Long pid) {
        for (int i = 0; i < itemDtoList.size(); i++) {
            ItemPo itemPo = new ItemPo();
            BeanUtils.copyProperties(itemDtoList.get(i),itemPo);
            //itemPo.setfBillBatchCode(itemDtoList.get(i).getfBillBatchCode());
            //itemPo.setfWarehouseId(itemDtoList.get(i).getfWarehouseId());
            //itemPo.setfWarehouseName(itemDtoList.get(i).getfWarehouseName());
            //itemPo.setfNumber(itemDtoList.get(i).getfNumber());
            //itemPo.setfBillNo1(itemDtoList.get(i).getfBillNo1());
            //itemPo.setfBillNo2(itemDtoList.get(i).getfBillNo2());
            itemPo.setfId(CommonUtil.generateID());
            itemPo.setfCreateTime(LocalDateTime.now());
            itemPo.setfUpdateTime(LocalDateTime.now());
            itemPo.setfPid(pid);
            itemPo.setfVersion(0);
            itemMapper.insert(itemPo);
        }
        return false;
    }

    @Override
    public boolean insert(ItemDto itemDto) {
        ItemPo itemPo = new ItemPo();
        itemPo.setfBillBatchCode(itemDto.getfBillBatchCode());
        itemPo.setfWarehouseId(itemDto.getfWarehouseId());
        itemPo.setfNumber(itemDto.getfNumber());
        itemPo.setfBillNo1(itemDto.getfBillNo1());
        itemPo.setfBillNo2(itemDto.getfBillNo2());
        itemMapper.insert(itemPo);
        return false;
    }

    @Override
    public List<ItemPo> getItemListByDestroyNo(String fDestroyNo) {
        QueryWrapper<ItemPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_pid", fDestroyNo);
        List<ItemPo> ItemPos = itemMapper.selectList(queryWrapper);
        return ItemPos;
    }

    @Override
    public int updateItemInfo(List<ItemDto> itemDtoList) {
       for(int i = 0; i < itemDtoList.size(); i++){
           ItemPo itemPo = new ItemPo();
           BeanUtils.copyProperties(itemDtoList.get(i),itemPo);
           itemMapper.updateById(itemPo);
       }
       return 0;
    }

    @Override
    public int deleteItemInfoByDestroyNo(String fPid) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("f_pid",fPid);
        return itemMapper.deleteByMap(map);
    }
}
