package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.FabItemBillDao;
import com.bosssoft.ecds.entity.vo.ItemBillVo;
import com.bosssoft.ecds.service.FabItemBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuliming
 * @since 2020-08-06
 */
@Service
public class FabItemBillServiceImpl implements FabItemBillService {
    @Autowired
    FabItemBillDao fabItemBillDao;
    @Override
    public List<ItemBillVo> selectItemByBillCode(Integer pagenum, Integer pagesize, String billCode) {
        Page page = new Page(pagenum, pagesize);


        return null;
    }
}
