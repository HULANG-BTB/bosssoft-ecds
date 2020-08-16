package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.FabItemBillDao;
import com.bosssoft.ecds.entity.po.FabItemBillPO;
import com.bosssoft.ecds.service.ItemBillIService;
import org.springframework.stereotype.Service;

/**
 * @ClassName :  ItemBillIServiceImpl
 * @Description : 实现批量添加
 * @Author : wuliming
 * @Date: 2020-08-10 22:10
 */
@Service
public class ItemBillIServiceImpl extends ServiceImpl<FabItemBillDao, FabItemBillPO> implements ItemBillIService {
}
