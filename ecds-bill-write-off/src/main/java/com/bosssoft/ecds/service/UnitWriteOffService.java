package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.po.WriteOffPO;

/**
 * @author hujierong
 * @date 2020-8-12
 */
public interface UnitWriteOffService {
    /**
     * 分页查询
     * @return 分页对象
     */
    IPage<WriteOffPO> selectPage();
}
