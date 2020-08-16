package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.WriteOffPO;

import java.util.List;

public interface FinancialWriteOffService {

    // 获取单位端传来的核销申请
    List<Object> receive(Object object);

    // 将核销申请退回单位端
    boolean sendBack(List<Object> list);

    // 获取核销申请的详情
    Object getDetails(Object object);

    // 获取单位电子档案
    Object getUnitDetails(Object object);

    // 存入审验结果
    boolean setResult(Object object);

}
