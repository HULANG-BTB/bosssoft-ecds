package com.bosssoft.ecds.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.FabItemBillDao;
import com.bosssoft.ecds.entity.po.FabItemBillPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.vo.itembillvo.BillItemsInsertVO;
import com.bosssoft.ecds.entity.vo.itembillvo.BillItemsVO;
import com.bosssoft.ecds.entity.vo.itembillvo.ItemBillVO;
import com.bosssoft.ecds.entity.vo.itembillvo.SelectItemVO;
import com.bosssoft.ecds.service.FabItemBillService;
import com.bosssoft.ecds.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


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
    private FabItemBillDao fabItemBillDao;
    @Autowired
    private ItemBillIServiceImpl itemBillIService;
    @Autowired
    private ItemService itemService;

    @Override
    public IPage<ItemBillVO> selectItemWithBillCode(SelectItemVO selectItemVO) {
        Page<ItemBillVO> page = new Page(selectItemVO.getPageNum(), selectItemVO.getPageSize());
        String itemname = selectItemVO.getItemName();
        String billCode = selectItemVO.getBillCode();
        return fabItemBillDao.selectItemWithBillCode(page, beautifyBillCode(billCode), beautifyItemName(itemname));
    }

    @Override
    public boolean insertIntoItemBill(BillItemsInsertVO itemsInsertVO) {
        String billCode = itemsInsertVO.getBillCode();
        String itemId = itemsInsertVO.getItemId();
        FabItemBillPO fabItemBillPO = new FabItemBillPO();
        fabItemBillPO.setFItemIdCode(itemId);
        fabItemBillPO.setFBillCode(billCode);
        fabItemBillPO.setFIsEnabled(true);
        fabItemBillPO.setVersion(0);
        return fabItemBillDao.insert(fabItemBillPO) == 1;
    }

    @Override
    public boolean insertBatchItemBill(BillItemsVO billItemsVO) {
        List<String> itemIds = billItemsVO.getItemIds();
        String billCode = billItemsVO.getBillCode();
        List<FabItemBillPO> fabItemBillPOS = new ArrayList<>(itemIds.size());
        for (int i = 0; i < itemIds.size(); i++) {
            FabItemBillPO fabItemBillPO = new FabItemBillPO();
            fabItemBillPO.setFItemIdCode(itemIds.get(i));
            fabItemBillPO.setFBillCode(billCode);
            fabItemBillPOS.add(fabItemBillPO);
            fabItemBillPO.setVersion(0);
        }
        return itemBillIService.saveBatch(fabItemBillPOS);
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
    public boolean checkItemBill(BillItemsInsertVO itemsInsertVO) {
        String billCode = itemsInsertVO.getBillCode();
        String itemId = itemsInsertVO.getItemId();
        FabItemBillPO fabItemBillPO = fabItemBillDao.selectOne(new LambdaQueryWrapper<FabItemBillPO>().eq(FabItemBillPO::getFBillCode, billCode)
                .eq(FabItemBillPO::getFItemIdCode, itemId).eq(FabItemBillPO::getFIsEnabled, true));
     return  fabItemBillPO!=null;
    }


    @Override
    public IPage<ItemPO> selectNoContactItem(SelectItemVO selectItemVO) {
        // 设置分页信息
        Page<ItemPO> page = new Page(selectItemVO.getPageNum(), selectItemVO.getPageSize());
        String billCode = selectItemVO.getBillCode();
        return fabItemBillDao.selectNoContactItem(page, beautifyBillCode(billCode), beautifyItemName(selectItemVO.getItemName()));
    }

    public String beautifyItemName(String itemName) {
        String vueNull = "null";
        if (vueNull.equals(itemName) || itemName == null ||itemName.equals("")) {
            return "";
        } else {
            return '%' + itemName + '%';
        }
    }

    public String beautifyBillCode(String billCode) {
        String vueNull = "null";
        if (vueNull.equals(billCode) || billCode == null || billCode.equals("")) {
            return "";
        } else {
            return billCode;
        }
    }
}
