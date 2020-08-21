package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public interface BillService {

    /**
     * 从票据池取票
     * @param retrieveBillDto 包含票据编码、取票数量、操作人、操作人id等信息
     * @return List<BillPo>
     */
    List retrieveBill(RetrieveBillDto retrieveBillDto);

    /**
     * 向票据池放入票据
     * @param billDto 票据代码、票号起始、票号中止、操作人、操作人id等信息
     * @return int
     */
    int createBill(BillDto billDto);

    /**
     * 获取票据池剩余记录数量
     * @param table
     * @return int
     */
    int retrieveNumber(String table);
}
