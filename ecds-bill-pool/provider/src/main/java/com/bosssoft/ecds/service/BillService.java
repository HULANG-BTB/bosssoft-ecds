package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.BillDto;
import com.bosssoft.ecds.entity.dto.RetrieveBillDto;

import java.util.List;

public interface BillService {

    List retrieveBill(RetrieveBillDto retrieveBillDto);

    int createBill(BillDto billDto);

    int retrieveNumber(String table);
}
