package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.service.fallback.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ecds-template1", fallback = FallBack.class)
public interface SignatureService {
    /**
     * 电子签名
     * @return
     */
    @RequestMapping("/")
    String sign();
}
