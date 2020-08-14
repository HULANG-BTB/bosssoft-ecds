package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuke
 * @since 2020-08-13
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, ItemPO> implements ItemService {

}
