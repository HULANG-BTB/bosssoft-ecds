package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.vo.ItemBillVo;

import java.util.List;

public interface FabItemBillService {

    public List<ItemBillVo>  selectItemByBillCode(Integer pagenum, Integer pagesize, String billCode);
}
