package com.bosssoft.usm.service.impl;

import com.bosssoft.usm.dao.FabAgenBillMapper;
import com.bosssoft.usm.entity.po.FabAgenBillPO;
import com.bosssoft.usm.service.AbstractService;
import com.bosssoft.usm.service.FabAgenBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */
@Service
public class FabAgenBillServiceImpl extends AbstractService<FabAgenBillPO, FabAgenBillMapper>
    implements FabAgenBillService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(FabAgenBillPO bean) {
        super.update(bean);
    }
}
