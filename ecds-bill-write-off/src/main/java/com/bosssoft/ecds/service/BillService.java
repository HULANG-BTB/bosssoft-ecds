package com.bosssoft.ecds.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 库存管理服务
 * 这里用Feign进行Http的请求结果的解析和封装
 */
@FeignClient(value = "ecds-bill-invocing")
public interface BillService {

    @RequestMapping(value = "/billInvoicing/getUneCbillById", method = RequestMethod.GET)
    String getBillInfoList(@RequestParam("billId") String billId);

}
