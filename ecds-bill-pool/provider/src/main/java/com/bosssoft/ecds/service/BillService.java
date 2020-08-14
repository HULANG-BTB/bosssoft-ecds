package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.BillDto;

import java.util.List;

public interface BillService {

    List retrieveBill(int number);

    int createBill(List<BillDto> list);

    int retrieveNumber(String table);
}
