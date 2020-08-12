package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.domain.Bill;
import com.bosssoft.ecds.dto.SignedBillDto;
import com.bosssoft.ecds.service.ISignService;
import com.bosssoft.ecds.util.ResponseUtils;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/12
 * @Content:
 */
@Api("签名与验签控制器，提供签名与验签")
@RestController
@DefaultProperties(defaultFallback = "fallBackToProtect",
        commandProperties = {@HystrixProperty(
                name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
public class SignController {

    @Resource
    private ISignService signService;

    @PostMapping("/sign/sign")
    @ApiOperation("签名")
    @HystrixCommand
    public String sign(@ApiParam("需要签名的票据信息") Bill bill) throws Exception {
        SignedBillDto signedBillDto = signService.sign(bill);
        return ResponseUtils.getResponse(200, "签名成功", signedBillDto);
    }

    @PostMapping("/sign/verifySign")
    @ApiOperation("验证签名")
    @HystrixCommand
    public String verifySign(@ApiParam("签名信息类") SignedBillDto signedBill) throws Exception {
        return signService.verifySign(signedBill) ? ResponseUtils.getResponse(200,"验签成功",true):ResponseUtils.getResponse(200,"验签失败",false);
    }

    public String fallBackToProtect(){
        return ResponseUtils.getResponse(500,"服务器正忙，请稍后再访问","/(ㄒoㄒ)/~~");
    }

}
