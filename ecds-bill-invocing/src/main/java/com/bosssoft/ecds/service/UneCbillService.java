package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;

public interface UneCbillService {
    UneCbill getUneCBillById(String id);

    IPage<UneCbill> selectUnecBillPage(Page<UneCbill> page);

    int addUneCbill(UneCbill uneCbill);
}
