package com.bosssoft.ecds.service.client;

import com.bosssoft.ecds.response.QueryResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "nacos-service-usm")
public interface BillService {

    /**
     * 开票
     * @return
     */
    @GetMapping("/testKai")
    QueryResponseResult getBill();
}
