package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.fallback.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Component
@FeignClient(value = "ecds-template", fallback = FallBack.class)
public interface TemplateService {
    /**
     * 获取模板
     * @return
     */
    @PostMapping("/ecds-template/pdf/getRemoteAddress")
    QueryResponseResult getTemplate(@RequestBody NontaxBillDTO billDTO)throws ExecutionException, InterruptedException;

    /**
     * 获取模板
     * @return
     */
    @PostMapping("/ecds-template/image/getRemoteAddress")
    QueryResponseResult getImgTemplate(@RequestBody NontaxBillDTO billDTO)throws ExecutionException, InterruptedException;
}
