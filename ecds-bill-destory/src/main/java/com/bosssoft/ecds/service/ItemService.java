package com.bosssoft.ecds.service;

import com.bosssoft.ecds.dao.ItemMapper;
import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.dto.ItemDto;
import com.bosssoft.ecds.entity.po.ItemPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
public interface ItemService extends IService<ItemPo> {
    boolean insertItemInfo(List<ItemDto> itemDtoList, Long pid);

    boolean insert(ItemDto itemDto);

    /**
     * @description: 根据主表票据销毁申请ID查明细表
     * @param: [fDestroyNo]
     * @return: java.util.List<com.bosssoft.ecds.entity.po.ItemPo>
     * @date: 2020/8/17
     */
    List<ItemPo> getItemListByDestroyNo(String fDestroyNo);

    int deleteItemInfoByDestroyNo(String fPid);

    int updateItemInfo(List<ItemDto> itemDtoList);

}
