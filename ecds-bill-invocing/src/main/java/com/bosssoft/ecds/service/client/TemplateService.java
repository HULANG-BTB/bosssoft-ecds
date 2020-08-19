package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.service.fallback.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ExecutionException;

@Component
@FeignClient(value = "ecds-template", fallback = FallBack.class)
public interface TemplateService {
    /**
     * 获取模板
     * @return
     */
    @PostMapping("/image/getRemoteAddress")
    String getTemplate(@RequestBody NontaxBillDTO nontaxBillDTO)throws ExecutionException, InterruptedException;
}
