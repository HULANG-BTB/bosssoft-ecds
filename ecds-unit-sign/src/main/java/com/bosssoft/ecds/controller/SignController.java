package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dto.SignedDataDto;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
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
    public QueryResponseResult sign(@ApiParam("需要签名的票据信息") String data) throws Exception {
        SignedDataDto signedDataDto = signService.sign(data);
        return new QueryResponseResult(CommonCode.SUCCESS, signedDataDto);
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
    public ResponseResult verifySign(@ApiParam("签名信息类") @RequestBody SignedDataDto signedData) throws NoSuchProviderException,
            CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, DecoderException {
        return signService.verifySign(signedData) ? ResponseResult.SUCCESS():ResponseResult.FAIL();
    }

    /**
     * 服务降级默认fallback方法
     * @return
     */
    public ResponseResult fallBackToProtect(){
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

}
