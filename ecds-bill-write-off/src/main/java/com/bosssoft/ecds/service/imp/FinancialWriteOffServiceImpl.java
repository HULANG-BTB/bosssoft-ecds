package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.financial.WriteOffMapper;
import com.bosssoft.ecds.entity.po.WriteOffPO;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-12
 */
@Service
public class FinancialWriteOffServiceImpl implements FinancialWriteOffService {

    @Autowired(required = false)
    private WriteOffMapper writeOffMapper;

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<WriteOffPO> get() {
        return writeOffMapper.selectList(null);
    }
}
