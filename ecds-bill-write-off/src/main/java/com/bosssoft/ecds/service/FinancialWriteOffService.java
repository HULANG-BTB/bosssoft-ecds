package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.WriteOffPO;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-12
 */
public interface FinancialWriteOffService {

    // 增
    void save();

    // 删
    void delete();

    // 改
    void update();

    // 查
    List<WriteOffPO> get();
}
