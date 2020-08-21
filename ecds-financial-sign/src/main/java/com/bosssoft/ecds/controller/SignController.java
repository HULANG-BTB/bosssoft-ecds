package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dto.SignedDataDto;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.SignCode;
import com.bosssoft.ecds.service.ISignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/12
 * @Content: 签名控制器
 */
@Api("签名与验签控制器，提供签名与验签")
@RestController
@DefaultProperties(defaultFallback = "fallBackToProtect",
        commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")},
        ignoreExceptions = Exception.class)
public class SignController {

    @Resource
    private ISignService signService;

    /**
     * 签名
     * @param signedData
     * @return
     * @throws Exception
     */
    @PostMapping("/sign")
    @ApiOperation("签名")
    @HystrixCommand
        public QueryResponseResult sign(@ApiParam("签名信息类") @RequestBody SignedDataDto signedData) throws Exception {
        if (signService.verifySign(signedData) == true){
            SignedDataDto signedDataDto = signService.sign(signedData);
            return new QueryResponseResult<SignedDataDto>(CommonCode.SUCCESS, signedDataDto);
        }
        return new QueryResponseResult(SignCode.DENIED,null);
    }

    /**
     * 服务降级默认fallback方法
     * @return
     */
    public QueryResponseResult fallBackToProtect(){
        return new QueryResponseResult(CommonCode.SERVER_ERROR,null);
    }

}
