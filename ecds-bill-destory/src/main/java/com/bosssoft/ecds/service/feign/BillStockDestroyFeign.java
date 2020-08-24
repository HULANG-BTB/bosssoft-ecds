package com.bosssoft.ecds.service.feign;

import com.bosssoft.ecds.entity.dto.DestroyDto;
import com.bosssoft.ecds.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: qiuheng
 * @create: 2020-08-20 09:05
 **/
@FeignClient("nacos-service-usm")
public interface BillStockDestroyFeign {

    @PostMapping("/destroyStockBill")
    public int destroyStockBill(@RequestBody DestroyDto destroyDto);

}
