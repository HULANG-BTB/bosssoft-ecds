package com.bosssoft.ecds.template.controller;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import com.bosssoft.ecds.template.service.PdfService;
import com.bosssoft.ecds.template.util.ResponseBody;
import com.bosssoft.ecds.template.util.response.CommonCode;
import com.bosssoft.ecds.template.util.response.QueryResponseResult;
import com.bosssoft.ecds.template.util.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

@Api("PDF生成相关控制器")
@Slf4j
@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    PdfService pdfService;

    /**
     * 从本地文件中下载pdf文件
     *
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
     *
     * @param billDTO 非税票据DTO
     * @return ok
     */

    @ApiOperation("根据票据信息生成pdf文件，储存在本地")
    @PostMapping("/createPdf")
    public ResponseResult createPdf(@RequestBody NontaxBillDto billDTO) {
        boolean success = pdfService.createPdf(billDTO);
        return success ? new ResponseResult(CommonCode.SUCCESS)
                : new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据票据信息生成pdf文件，并返回本地下载链接
     *
     * @param billDTO 非税票据DTO
     * @return 可以访问的url
     */

    @ApiOperation("根据票据信息生成pdf文件，并返回本地下载链接")
    @PostMapping("/getAddress")
    public ResponseResult getAddress(@RequestBody NontaxBillDto billDTO, HttpServletRequest request) {
        // 裁剪得到根目录地址，例如：http://localhost:8080
        int l = request.getRequestURI().length();
        StringBuffer host = request.getRequestURL();
        host.delete(host.length() - l, host.length());

        boolean success = pdfService.createPdf(billDTO);

        if (success) {
            String url = host + "/pdf/output/"
                    + billDTO.getBillCode() + billDTO.getSerialCode() + ".pdf";
            return new QueryResponseResult<>(CommonCode.SUCCESS, url);
        } else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }

    /**
     * 根据票据信息生成pdf文件，并返回远程（OSS）下载链接
     *
     * @param billDTO 非税票据DTO
     * @return 可以访问的url
     */

    @ApiOperation("根据票据信息生成pdf文件，并返回远程（OSS）下载链接")
    @PostMapping("/getRemoteAddress")
    public ResponseResult getRemoteAddress(
            @RequestBody
                    NontaxBillDto billDTO,
            @RequestParam(defaultValue = "2592000000")
            @ApiParam(value = "过期时间(毫秒)，默认是1个月", example = "0")
                    Long expireTime) {
        String url = pdfService.getRemoteAddress(billDTO, true, expireTime);
        log.info(url);

        if ("".equals(url)) {
            return new ResponseResult(CommonCode.FAIL);
        } else {
            return new QueryResponseResult<>(CommonCode.SUCCESS, url);
        }
    }

    /**
     * 返回一个样板PDF
     */
    @ApiOperation("返回一个样板 PDF")
    @GetMapping(value = "/template", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getTemplate() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        NontaxBillDto billDTO = new NontaxBillDto();
        billDTO.setBillCode("01160201");
        billDTO.setItems(new ArrayList<>());
        pdfService.createPdf(billDTO, output);
        return output.toByteArray();
    }
}
