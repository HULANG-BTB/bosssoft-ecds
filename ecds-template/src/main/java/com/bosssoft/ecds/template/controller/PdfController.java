package com.bosssoft.ecds.template.controller;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.template.service.PdfService;
import com.bosssoft.ecds.template.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api("PDF生成相关控制器")
@Slf4j
@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    PdfService pdfService;

    /**
     * 从本地文件中下载pdf文件
     * @param filename 票据代码+票据号码
     * @return 文件字节数组
     */

    @ApiOperation("从本地文件中下载pdf文件")
    @GetMapping(value = "/output/{fileName}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] genPdf(@PathVariable("fileName") @ApiParam("8位代码+10位号码") String filename) {
        return pdfService.getBytesFromFile(filename);
    }

    /**
     * 根据票据信息生成pdf文件，储存在本地
     * @param billDTO 非税票据DTO
     * @return ok
     */

    @ApiOperation("根据票据信息生成pdf文件，储存在本地")
    @PostMapping("/createPdf")
    public ResponseBody createPdf(@RequestBody NontaxBillDTO billDTO) {
        pdfService.createPdf(billDTO);
        return ResponseBody.ok();
    }

    /**
     * 根据票据信息生成pdf文件，并返回本地下载链接
     * @param billDTO 非税票据DTO
     * @return 可以访问的url
     */

    @ApiOperation("根据票据信息生成pdf文件，并返回本地下载链接")
    @PostMapping("/getAddress")
    public ResponseBody getAddress(@RequestBody NontaxBillDTO billDTO, HttpServletRequest request) {
        // 裁剪得到根目录地址，例如：http://localhost:8080
        int l = request.getRequestURI().length();
        StringBuffer host = request.getRequestURL();
        host.delete(host.length() - l, host.length());

        pdfService.createPdf(billDTO);

        String url = host + "/pdf/output/"
                + billDTO.getBillCode() + billDTO.getSerialCode() + ".pdf";
        return ResponseBody.ok(url);
    }

    /**
     * 根据票据信息生成pdf文件，并返回远程（OSS）下载链接
     * @param billDTO 非税票据DTO
     * @return 可以访问的url
     */

    @ApiOperation("根据票据信息生成pdf文件，并返回远程（OSS）下载链接")
    @PostMapping("/getRemoteAddress")
    public ResponseBody getRemoteAddress(@RequestBody NontaxBillDTO billDTO) {
        String url = pdfService.getRemoteAddress(billDTO, false);
        log.info(url);
        return ResponseBody.ok(url);
    }
}
