package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.SignedDataDto;
import com.bosssoft.ecds.entity.po.Sign;

public interface SignService {
    SignedDataDto getSignData(String billId, String billNo);

    int addSign(Sign sign);
}
