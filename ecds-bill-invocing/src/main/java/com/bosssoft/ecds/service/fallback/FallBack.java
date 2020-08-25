package com.bosssoft.ecds.service.fallback;

import com.bosssoft.ecds.entity.dto.AgenInfoDTO;
import com.bosssoft.ecds.entity.dto.ArrearDTO;
import com.bosssoft.ecds.entity.dto.ItemInfoDTO;
import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.client.TemplateService;
import com.bosssoft.ecds.service.client.TestService;
import com.bosssoft.ecds.service.client.UnitManagerService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class FallBack implements UnitManagerService, TestService , TemplateService {

    @Override
    public ArrearDTO isArrear(String unitName) {
        return null;
    }

    /**
     * 查询出现异常时进行服务降级
     * @param unitName
     * @return
     */
    @Override
    public AgenInfoDTO getDetailByUnitName(String unitName) {
        //TODO
        return null;
    }

    @Override
    public QueryResponseResult getItemInfo(String unitName) {
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

    @Override
    public QueryResponseResult getImgTemplate(NontaxBillDTO billDTO) throws ExecutionException, InterruptedException {
        return null;
    }
}
