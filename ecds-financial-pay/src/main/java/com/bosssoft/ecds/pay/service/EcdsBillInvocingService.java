package com.bosssoft.ecds.pay.service;

import com.bosssoft.ecds.response.QueryResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName EcdBillAccountingService
 * @Description 调用单位端接口查询缴费明细
 * @auther wangpeng
 * @Date 2020/8/24 9:09
 * @Version 1.0
 **/
@FeignClient(value = "bill-invocing")
public interface EcdsBillInvocingService {

    @GetMapping("/billInvoicing/getItems")
    QueryResponseResult getItems (@RequestParam("fPayerTel") String fPayerTel, @RequestParam("checkCode")String checkCode);

}
