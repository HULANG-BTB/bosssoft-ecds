package com.bosssoft.ecds.service.fallback;

import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.entity.dto.TemplateDto;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.client.TemplateService;
import com.bosssoft.ecds.service.client.TestService;
import com.bosssoft.ecds.service.client.UnitManagerService;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements UnitManagerService, TestService , TemplateService {

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
    public boolean isOutLimit(String unitName) {
        return false;
    }

    @Override
    public String hasAvailableBill(String unitName) {
        return null;
    }

    @Override
    public String test(String test) {
        throw new RuntimeException();
    }

    @Override
    public QueryResponseResult getTemplate(NontaxBillDTO nontaxBillDTO) {
        return null;
    }
}
