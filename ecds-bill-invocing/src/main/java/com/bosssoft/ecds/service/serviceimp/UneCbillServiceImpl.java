package com.bosssoft.ecds.service.serviceimp;

import com.bosssoft.ecds.dao.UneCbillMapper;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UneCbillServiceImpl implements UneCbillService {

    @Autowired
    private UneCbillMapper uneCbillMapper;

    @Override
    public UneCbill getUneCBillById(long id) {
        return uneCbillMapper.selectById(id);
    }
}
