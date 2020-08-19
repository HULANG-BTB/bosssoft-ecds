package com.bosssoft.ecds.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存管理服务
 * 这里用Feign进行Http的请求结果的解析和封装
 */
@FeignClient(value = "ecds-bill-invocing")
public interface BillService {

    @RequestMapping(value = "/billInvoicing/getUneCbillById", method = RequestMethod.GET)
    String getBillInfoList(@RequestParam("billId") String billId);

    /**
     * 根据日期获取开票的信息
     * @param start 开始日期
     * @param end 结束日期
     * @return 接口调用结果
     */
    @GetMapping("/billInvoicing/writeOffInfo")
    String getWriteOffInfo(@RequestParam("start") String start, @RequestParam("end") String end);
}
