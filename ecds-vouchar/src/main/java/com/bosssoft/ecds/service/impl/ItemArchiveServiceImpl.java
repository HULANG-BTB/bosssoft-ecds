package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ItemArchiveDao;
import com.bosssoft.ecds.entity.dto.ItemAvailableDTO;
import com.bosssoft.ecds.entity.po.ItemArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.service.ItemArchiveService;
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

    @Override
    public List<ItemAvailableDTO> getItemAvailableInfos(CommonQuery query) {
        Page<ItemArchivePO> pager = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<ItemArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(ItemArchivePO::getAgenCode, query.getAgenCode())
                .orderByDesc(ItemArchivePO::getCreateTime);
        Page<ItemArchivePO> page = page(pager, lambdaQuery);
        List<ItemArchivePO> list = page.getRecords();
        List<ItemAvailableDTO> res = new ArrayList<>();
        list.forEach(
                po -> {
                    ItemAvailableDTO billApplyDTO = BeanUtil.toBean(po, ItemAvailableDTO.class);
                    res.add(billApplyDTO);
                }
        );
        return res;
    }

    @Override
    public void finaItemAvailableArchive() {
        /*收集信息归档*/
        List<ItemArchivePO> itemArchivePOS = itemArchiveDao.collectItemAvailableInfo();
        saveBatch(itemArchivePOS);
    }

}
