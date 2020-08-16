package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ItemArchiveDao;
import com.bosssoft.ecds.entity.dto.ItemAvailableDTO;
import com.bosssoft.ecds.entity.po.ItemArchivePO;
import com.bosssoft.ecds.service.ItemArchiveService;
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
public class ItemArchiveServiceImpl extends ServiceImpl<ItemArchiveDao, ItemArchivePO> implements ItemArchiveService {
    @Autowired
    private ItemArchiveDao itemArchiveDao;

    @Override
    public List<ItemAvailableDTO> getItemAvailableInfos() {
        /*
         * 分页查询  分页对象
         */
        //itemArchiveDao.selectList();
        return null;
    }

    @Override
    public void finaItemAvailableArchive() {
        /*收集信息归档*/
        List<ItemArchivePO> itemArchivePOS = itemArchiveDao.collectItemAvailableInfo();
        saveBatch(itemArchivePOS);
    }

    @Override
    public ItemAvailableDTO getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public void unitItemAvailableArchive() {

    }
}
