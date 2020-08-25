package com.bosssoft.ecds.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: qiuheng
 * @create: 2020-08-17 23:32
 **/
@FeignClient("nacos-payment-provider")
public interface TestFeign {
    @GetMapping(value = "/payment/nacos/{id}")
    String test(@PathVariable("id") Integer id);
}
