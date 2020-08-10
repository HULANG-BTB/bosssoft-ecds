package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.FabItemBillDao;
import com.bosssoft.ecds.entity.po.FabItemBillPO;
import com.bosssoft.ecds.entity.vo.ItemBillVO;
import com.bosssoft.ecds.service.FabItemBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
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
    public IPage<ItemBillVO> selectItemByBillCode(Integer pagenum, Integer pagesize, String billCode) {
        Page<ItemBillVO> page = new Page(pagenum, pagesize);
        return fabItemBillDao.selectItemByBillCode(page, billCode);
    }

    @Override
    public boolean insertIntoItemBill(String billCode, String itemId) {

        FabItemBillPO fabItemBillPO = new FabItemBillPO();
        fabItemBillPO.setFItemIdCode(itemId);
        fabItemBillPO.setFBillCode(billCode);
        fabItemBillPO.setFIsEnabled(true);
        fabItemBillPO.setVersion(0);
        return fabItemBillDao.insert(fabItemBillPO) == 1;
    }

    @Override
    public boolean deleteFromItemBill(Long id) {
        return fabItemBillDao.deleteById(id) == 1;
    }

    @Override
    public boolean turnEnabled(Long id) {
        FabItemBillPO fabItemBillPO = fabItemBillDao.selectById(id);
        boolean result = !fabItemBillPO.getFIsEnabled();
        fabItemBillPO.setFIsEnabled(result);
        fabItemBillDao.updateById(fabItemBillPO);
        return result;
    }

    @Override
    public boolean checkItemBill(String billCode, String itemId) {
        FabItemBillPO fabItemBillPO = fabItemBillDao.selectOne(new LambdaQueryWrapper<FabItemBillPO>().eq(FabItemBillPO::getFBillCode, billCode)
                .eq(FabItemBillPO::getFItemIdCode, itemId).eq(FabItemBillPO::getFIsEnabled, true));
        if (fabItemBillPO != null) {
            return true;
        }
        return false;
    }
}
