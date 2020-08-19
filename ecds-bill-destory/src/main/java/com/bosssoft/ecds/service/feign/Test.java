package com.bosssoft.ecds.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: qiuheng
 * @create: 2020-08-17 22:24
 **/
@FeignClient("ecds-bill-invocing")
public interface Test {
    @RequestMapping("/billInvoicing/getBillByIdAndCheckCode")
    String test(@RequestParam("billId") String billId,@RequestParam("checkCode") String checkCode);
}
