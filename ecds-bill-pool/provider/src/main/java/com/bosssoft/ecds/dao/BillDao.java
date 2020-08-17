package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.po.BillPo;

import java.util.List;

public interface BillDao {

    int insertBill(String table, List<BillPo> list);

    int retrieveNumber(String table);

    List<String> retrieveBillTypeCode();

    List retrieveList(int number);

    int deleteList(List<Integer> list);
}
