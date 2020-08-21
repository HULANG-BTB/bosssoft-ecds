package com.bosssoft.ecds.controller;

import cn.hutool.core.convert.Convert;
import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.entity.dto.SignedDataDto;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.SignService;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.FIanacialSignService;
import com.bosssoft.ecds.service.client.SignatureService;
import com.bosssoft.ecds.service.client.TemplateService;
import com.bosssoft.ecds.util.AliyunOSSUtil;
import com.bosssoft.ecds.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import feign.Response;
import java.io.*;
import java.net.URL;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/verify")
public class SignController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private UneCbillService uneCbillService;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Autowired
    private FIanacialSignService fIanacialSignService;

    @Autowired
    private SignService signService;

    @Autowired
    private SignatureService signatureService;

    /**
     * 获取盖章后的模板
     * @return
     */
    @RequestMapping("/sign")
    public ResponseResult sign(String billId, String billNo) throws Exception {
        //验证单位签名
        SignedDataDto signedDataDto = signService.getSignData(billId, billNo);
        QueryResponseResult response = fIanacialSignService.sign(signedDataDto);
        if (!response.isSuccess()) {
            return ResponseResult.FAIL();
        }
        SignedDataDto signedDataDto1 = Convert.convert(SignedDataDto.class, response.data);

        //获取模板
        UneCbill uneCbill = uneCbillService.getUneCbillByIdAndNo(billId, billNo);
        if(uneCbill == null) {
            return ResponseResult.FAIL();
        }
        List<UneCbillItem> uneCbillItems = uneCbillService.getItems(String.valueOf(uneCbill.getFId()));
        NontaxBillDTO nontaxBillDTO = uneCbillService.getNontaxBillDto(uneCbill, uneCbillItems);
        String url = Convert.convert(String.class, (templateService.getTemplate(nontaxBillDTO)).data);
        log.info(url);
        File file = FileUtil.downFile(url, "..");
        //2 调用模板下载票据的pdf模板 --> 转成MultipartFile -->将MultipartFile  + String + String 通过签名服务器进行验证 -->得到签名后的pdf模板
        MultipartFile multipartFile = getMultipartFile(file);
        Response feign = signatureService.stamp(multipartFile,
                signedDataDto1.getUnitSignValue(),
                signedDataDto1.getFinanceSignValue());
        InputStream inputStream = feign.body().asInputStream();
        File pdfFile = new File("../pdfFile.pdf");
        outPutFile(inputStream, pdfFile);
        /**
         * 将pdf文件上传到oss，返回url地址
         */
        String objectName = "boss-bill/" + billId+billNo+".pdf";
        aliyunOSSUtil.upload(objectName, inputStream);
        URL pdfUrl = aliyunOSSUtil.temporaryUrl(objectName, 60 * 1000L);
        log.info(pdfUrl.toString());
        inputStream.close();
        //5 将url地址给到消息推送服务
        return ResponseResult.SUCCESS();
    }

    /**
     *将File转成MultipartFile
     * @param file
     * @return
     */
    public static MultipartFile getMultipartFile(File file) {
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("uploadFile",
                MediaType.TEXT_PLAIN_VALUE, true, file.getName());
        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }
        MultipartFile uploadFile = new CommonsMultipartFile(fileItem);
        return uploadFile;
    }

    /**
     * 将盖完章的pdf文件保存下来
     * @param inputStream
     * @param fileStamp
     * @throws IOException
     */
    public static void outPutFile(InputStream inputStream, File fileStamp) throws IOException {
        if (!fileStamp.exists()){
            fileStamp.createNewFile();
        }
        OutputStream outStream = new FileOutputStream(fileStamp);
        try {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,len));
                outStream.write(bytes, 0, len);
            }
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
