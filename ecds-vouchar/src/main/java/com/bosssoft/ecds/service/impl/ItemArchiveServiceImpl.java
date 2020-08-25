package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.exception.MyExceptionCode;
import com.bosssoft.ecds.dao.ItemArchiveDao;
import com.bosssoft.ecds.entity.dto.ItemAvailableDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemArchivePO;
import com.bosssoft.ecds.entity.query.CommonQuery;
import com.bosssoft.ecds.exception.ExceptionCast;
import com.bosssoft.ecds.service.ItemArchiveService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Slf4j(topic = "kafka_business_logger")
public class ItemArchiveServiceImpl extends ServiceImpl<ItemArchiveDao, ItemArchivePO> implements ItemArchiveService {
    @Autowired
    private ItemArchiveDao itemArchiveDao;

    @Override
    public PageDTO<ItemAvailableDTO> getItemAvailableInfos(CommonQuery query) {
        /*存放分页对象信息*/
        PageDTO<ItemAvailableDTO> pageDTO = new PageDTO<>();
        // 构造查询条件
        Page<ItemArchivePO> pager = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<ItemArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(ItemArchivePO::getAgenCode, query.getAgenCode())
                .orderByDesc(ItemArchivePO::getCreateTime);
        Page<ItemArchivePO> page = page(pager, lambdaQuery);
        // 赋予查询总数
        pageDTO.setTotal(page.getTotal());
        // 获取并且转换对象
        List<ItemArchivePO> list = page.getRecords();
        List<ItemAvailableDTO> dtos = MyBeanUtil.copyListProperties(list, ItemAvailableDTO::new);
        pageDTO.setData(dtos);
        return pageDTO;
    }

    @Override
    public void finaItemAvailableArchive() {
        /*收集信息归档*/
        List<ItemArchivePO> itemArchivePOS = itemArchiveDao.collectItemAvailableInfo();
        if (itemArchivePOS == null || itemArchivePOS.isEmpty()) {
            ExceptionCast.cast(MyExceptionCode.ITEM_AVAILABLE_DATE_EMPTY);
        }
        log.info("itemPOS => " + itemArchivePOS);
        saveBatch(itemArchivePOS);
    }

}
