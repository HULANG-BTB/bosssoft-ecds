package com.bosssoft.ecds.controller;

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
    public String stamp(@ApiParam("需要盖章的pdf文件") @RequestPart("uploadFile") MultipartFile uploadFile,
                        @ApiParam("单位签名") @RequestParam("unitSignValue") String unitSignValue,
                        @ApiParam("财政签名") @RequestParam("financeSignValue") String financeSignValue,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        //测试   成功================================
        request.getSession().setAttribute("1","2");
        boolean result = stampService.stamp(uploadFile, unitSignValue, financeSignValue, request, response);
        if (result){
            return ResponseUtils.getResponse(200,"成功盖章");
        } else {
            return ResponseUtils.getResponse(250,"签名不正确或签名已失效");
        }
    }

    @PostMapping("/test")
    public String test(){
        return "success";
    }

    /**
     * 服务降级默认fallback方法
     * @return
     */
    public String fallBackToProtect(){
        return ResponseUtils.getResponse(500,"服务器正忙，请稍后再访问","/(ㄒoㄒ)/~~");
    }

}
