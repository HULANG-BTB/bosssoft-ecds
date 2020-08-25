package com.bosssoft.ecds.service.serviceimp;

import com.bosssoft.ecds.service.VerifyService;
import org.springframework.stereotype.Service;

@Service
public class VerifyServiceImpl implements VerifyService {

    @Override
    public boolean isArrear(String unitName) {
        return false;
    }

    @Override
    public String getUnitBill(String unitName) {
        return null;
    }

    @Override
    public boolean isOutLimit(String unitName, int billCount) {
        return false;
    }
}
