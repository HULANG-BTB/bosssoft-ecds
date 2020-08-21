package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.response.SignCode;
import com.bosssoft.ecds.service.IStampService;
import com.bosssoft.ecds.util.ResponseUtils;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/15
 * @Content:
 */
@Api("对上传文件加盖电子印章")
@RestController
@DefaultProperties(defaultFallback = "fallBackToProtect",
        commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")},
        ignoreExceptions = Exception.class)
public class StampController {

    @Autowired
    private IStampService stampService;

    @PostMapping( value = "/stamp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("加盖电子印章")
    @HystrixCommand
    public ResponseResult stamp(@ApiParam("需要盖章的pdf文件") @RequestPart("uploadFile") MultipartFile uploadFile,
                                @ApiParam("单位签名") @RequestParam("unitSignValue") String unitSignValue,
                                @ApiParam("财政签名") @RequestParam("financeSignValue") String financeSignValue,
                                HttpServletResponse response) throws Exception {
        boolean result = stampService.stamp(uploadFile, unitSignValue, financeSignValue, response);
        if (result){
            return new ResponseResult(SignCode.STAMP_SUCCESS);
        } else {
            return new ResponseResult(SignCode.STAMP_FAILURE);
        }
    }

    /**
     * 服务降级默认fallback方法
     * @return
     */
    public ResponseResult fallBackToProtect(){
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

}
