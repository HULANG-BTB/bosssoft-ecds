package com.bosssoft.ecds.pay.service;

import com.bosssoft.ecds.pay.domain.vo.AccIntoInfoVO;
import com.bosssoft.ecds.pay.domain.vo.WaitAccountVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName EcdBillAccountingService
 * @Description 调用财政端接口
 * @auther wangpeng
 * @Date 2020/8/24 9:09
 * @Version 1.0
 **/
@FeignClient(value = "ecds-bill-accounting")
public interface EcdsBillAccountingService{

    @PostMapping("/account/getWaitAccount")
    public QueryResponseResult getWaitAccount(@RequestBody WaitAccountVO waitAccountVO);

    @PostMapping("/account/updateAccount")
    public ResponseResult updateAccount(@RequestBody AccIntoInfoVO accIntoInfoVO);
}


