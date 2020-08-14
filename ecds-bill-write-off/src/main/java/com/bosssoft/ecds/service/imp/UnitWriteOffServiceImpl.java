package com.bosssoft.ecds.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.financial.WriteOffMapper;
import com.bosssoft.ecds.entity.po.WriteOffPO;
import com.bosssoft.ecds.service.UnitWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hujierong
 * @date 2020-8-12
 */
@Service
public class UnitWriteOffServiceImpl implements UnitWriteOffService {
    @Autowired(required = false)
    private WriteOffMapper writeOffMapper;

    @Override
    public IPage<WriteOffPO> selectPage() {
        Page<WriteOffPO> page = new Page<>(1,2);
        return writeOffMapper.selectPage(page, null);
    }
}
