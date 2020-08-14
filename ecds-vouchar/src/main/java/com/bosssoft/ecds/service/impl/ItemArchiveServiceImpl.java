package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ItemArchiveDao;
import com.bosssoft.ecds.entity.dto.ItemAvailableDto;
import com.bosssoft.ecds.entity.po.AgenItemPO;
import com.bosssoft.ecds.entity.po.ItemArchivePO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.service.AgenItemService;
import com.bosssoft.ecds.service.ItemArchiveService;
import com.bosssoft.ecds.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * 归档可用
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class ItemArchiveServiceImpl extends ServiceImpl<ItemArchiveDao, ItemArchivePO> implements ItemArchiveService {
    @Autowired
    private ItemArchiveDao itemArchiveDao;
    @Autowired
    private AgenItemService agenItemService;
    @Autowired
    private ItemService itemService;

    @Override
    public List<ItemAvailableDto> getItemAvailableInfos() {
        /**
         * 分页查询  分页对象
         */
        //itemArchiveDao.selectList();
        return null;
    }

    @Override
    public void finaItemAvailableArchive() {
        List<ItemAvailableDto> res = new ArrayList<>();
        /*
         * 获取所有单位的可用项目信息
         */
        List<AgenItemPO> list = agenItemService.list();

        /*
         * 信息处理  数据字段不一致，不能利用hutool工具类
         */
        list.forEach(
                item -> {
                    ItemAvailableDto dto = new ItemAvailableDto();
                    dto.setAgenCode(item.getAgenIdcode());
                    dto.setItemCode(item.getItemCode());
                    res.add(dto);
                }
        );
        for (int i = 0; i < res.size(); i++) {
            ItemAvailableDto tempDto = res.get(i);
            LambdaQueryWrapper<ItemPO> lqw = new LambdaQueryWrapper<>();
            LambdaQueryWrapper<ItemPO> eq = lqw.eq(ItemPO::getItemId, tempDto.getItemCode());
            ItemPO one = itemService.getOne(eq);
            BeanUtil.copyProperties(one, tempDto);
            tempDto.setItemCode(one.getItemId());
            res.set(i, tempDto);
        }

        /*
         * 批量插入 可用项目归档
         * 如果直接使用po  利用hutool复制值会出现时间的覆盖
         */
        res.forEach(
                item -> {
                    ItemArchivePO po = new ItemArchivePO();
                    BeanUtil.copyProperties(item, po);
                    po.setVersion(1);
                    po.setLogicDelete(false);

                    /* 插入数据*/
                    itemArchiveDao.insert(po);
                }
        );
    }

    @Override
    public ItemAvailableDto getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public void unitItemAvailableArchive() {

    }
}
