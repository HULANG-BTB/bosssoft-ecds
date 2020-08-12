package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.po.BillPo;

import java.util.List;

public interface BillDao {

    int insertBill(List<BillPo> list);

    int retrieveNumber();

    List retrieveList(int number);

    int deleteList(List<Integer> list);
}
