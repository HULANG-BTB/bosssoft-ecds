package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dto.SignedDataDto;
import com.bosssoft.ecds.service.ISignService;
import com.bosssoft.ecds.util.ResponseUtils;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.DecoderException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;

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
     * @param data
     * @return
     * @throws Exception
     */
    @PostMapping("/sign")
    @ApiOperation("签名")
    @HystrixCommand
    public String sign(@ApiParam("需要签名的票据信息") String data) throws Exception {
        SignedDataDto signedDataDto = signService.sign(data);
        return ResponseUtils.getResponse(200, "签名成功", signedDataDto);
    }

    /**
     * 验证签名
     * @param signedData
     * @return
     * @throws Exception
     */
    @PostMapping("/verifySign")
    @ApiOperation("验证签名")
    @HystrixCommand
    public String verifySign(@ApiParam("签名信息类") @RequestBody SignedDataDto signedData) throws NoSuchProviderException,
            CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, DecoderException {
        return signService.verifySign(signedData) ? ResponseUtils.getResponse(200,"验签成功",true):ResponseUtils.getResponse(200,"验签失败",false);
    }

    /**
     * 服务降级默认fallback方法
     * @return
     */
    public String fallBackToProtect(){
        return ResponseUtils.getResponse(500,"服务器正忙，请稍后再访问","/(ㄒoㄒ)/~~");
    }

}
