package com.bosssoft.ecds.msg.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 13:58
 */
@FeignClient(value = "ecds-bill-invocing" )
public interface CheckClient {

    /**
     * 调用开票模块开放的接口，根据票据id和票据校验码查验票据
     * @param billId    票据Id
     * @param checkCode 票据校验码
     * @return 票据查验结果
     */
    @GetMapping("/billInvoicing/getBillByIdAndCheckCode")
    String getBillByIdAndCheckCode(@RequestParam("billId") String billId, @RequestParam("checkCode") String checkCode);

}