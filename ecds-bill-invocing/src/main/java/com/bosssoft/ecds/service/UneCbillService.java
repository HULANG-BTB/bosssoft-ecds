package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.vo.UneCbillVo;

import java.util.List;

public interface UneCbillService {
    UneCbillVo getUneCBillById(String id);

    IPage<UneCbillVo> selectUnecBillPage(Page<UneCbill> page);

    int addUneCbill(UneCbill uneCbill);

    int billCount();

    UneCbill getBillByIdAndCheckCode(QueryWrapper<UneCbill> queryWrapper);
}
