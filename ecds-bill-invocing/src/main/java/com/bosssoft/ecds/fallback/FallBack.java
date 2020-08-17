package com.bosssoft.ecds.fallback;

import com.bosssoft.ecds.service.UnitManagerService;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements UnitManagerService {

    @Override
    public boolean isArrear(String unitName) {
        return false;
    }

    /**
     * 查询出现异常时进行服务降级
     * @param unitName
     * @return
     */
    @Override
    public String getDetailByUnitName(String unitName) {
        //TODO
        return null;
    }

    @Override
    public String getItemList(String unitName) {
        return null;
    }

    @Override
    public boolean isOutLimit(String unitName, int billCount) {
        return false;
    }

    @Override
    public String hasAvailableBill(String unitName) {
        return null;
    }
}
