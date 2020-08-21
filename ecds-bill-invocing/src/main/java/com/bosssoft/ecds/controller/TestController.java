package com.bosssoft.ecds.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.MessageService;
import com.bosssoft.ecds.service.client.TestService;
import com.bosssoft.ecds.util.AliyunOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping(path = "/test")
public class TestController {


    @Autowired(required = false)
    private MessageService testService;

    @Autowired
    AliyunOSSUtil ossUtil;

    @GetMapping("/test")
    @ResponseBody
    public String test(@RequestParam("value") String value) throws ExecutionException, InterruptedException {
        testService.test(value);
        return "";
    }

    @GetMapping("/file")
    @ResponseBody
    public String getFile() {
        ossUtil.listFiles();
        File file = new File("https://gym-oss-test.oss-cn-shenzhen.aliyuncs.com/boss-bill/123456100.png?Expires=1600441138&OSSAccessKeyId=LTAI4G9QwvLCHMEmgYf2Jupe&Signature=aCnOQmdu2JlgSy6RPXjG46r9swg%3D");
        if (file != null) {
            return "ok";
        }
        return "error";
    }

    @RequestMapping("/download")
    public String testDownLoad() {
        File pdfFile = new File("../test.pdf");
        ossUtil.download("boss-bill/011602010000000020.pdf", pdfFile);
        return "ok";
    }
}
