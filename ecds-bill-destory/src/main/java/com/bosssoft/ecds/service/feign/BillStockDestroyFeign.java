package com.bosssoft.ecds.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: qiuheng
 * @create: 2020-08-20 09:05
 **/
//@FeignClient("")
public interface BillStockDestroyFeign {

    @PostMapping("")
    public int destroyStockBill(String fAgenIdCode, String fBillBatchCode, long fBillNo1, long fBillNo2);

}
