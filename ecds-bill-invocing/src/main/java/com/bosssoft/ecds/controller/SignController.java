package com.bosssoft.ecds.controller;

import cn.hutool.http.ContentType;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.TemplateService;
import com.bosssoft.ecds.util.AliyunOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private UneCbillService uneCbillService;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    /**
     * 获取盖章后的模板
     * @return
     */
    @RequestMapping("/sign")
    public String sign(String billId) throws FileNotFoundException {
        //1 通过billId查询相关票据的单位端签名得到 Dto + String + String
        UneCbill uneCbill = uneCbillService.getUneCBillById(billId);
        if(uneCbill != null) {

        }
        //2 调用模板下载票据的pdf模板 --> 转成MultipartFile
        File file = new File("本地文件路径");
        aliyunOSSUtil.download("/boss-bill/billId+billNo.pdf", file);
        /**
         * 将file转成MultipartFile
         */
        FileInputStream fileInputStream = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName())
        //2 将MultipartFile  + String + String 通过签名服务器进行验证 -->得到签名后的pdf模板
        //4 将pdf文件上传到oss，返回url地址
        //5 将url地址给到消息推送服务
        return "ok";
    }

}
