package com.bosssoft.ecds.template.controller;


import com.bosssoft.ecds.template.entity.dto.PrintTemplateDTO;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePO;
import com.bosssoft.ecds.template.entity.vo.PrintTemplateVO;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.util.BeanCopyUtil;
import com.bosssoft.ecds.template.util.response.CommonCode;
import com.bosssoft.ecds.template.util.response.QueryResponseResult;
import com.bosssoft.ecds.template.util.response.ResponseResult;
import com.bosssoft.ecds.template.util.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * <p>
 * 打印模板表 前端控制器
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/printTemplate")
public class PrintTemplateController {

    @Autowired
    PrintTemplateService printTemplateService;

    @GetMapping("/list")
    public ResponseResult listTemplate() {
        List<PrintTemplateDTO> templateDTOs = printTemplateService.listAll();
        List<PrintTemplateVO> templateVOs =
                BeanCopyUtil.copyListProperties(templateDTOs, PrintTemplateVO::new);
        return new QueryResponseResult<>(CommonCode.SUCCESS, templateVOs);
    }

    @PostMapping("/uploadTemplate")
    public ResponseResult uploadTemplate(@RequestParam String templateName,
                                         @RequestParam String billCode,
                                         @RequestParam String memo,
                                         @RequestParam("file")MultipartFile file) throws IOException {

        if (billCode.length() != 6) {
            return new ResponseResult(CommonCode.INVLIDATE);
        }

        String content = new String(file.getBytes());
        PrintTemplateDTO templateDTO = new PrintTemplateDTO();
        templateDTO.setRgnCode(billCode.substring(0,2));
        templateDTO.setTypeId(billCode.substring(2,4));
        templateDTO.setSortId(billCode.substring(4,6));
        templateDTO.setName(templateName);
        templateDTO.setMemo(memo);
        templateDTO.setTemplate(content);

        printTemplateService.add(templateDTO);

        return ResponseResult.SUCCESS();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseResult removeTemplate(@PathVariable Long id) {
        boolean success = printTemplateService.remove(id);
        return success ? ResponseResult.SUCCESS() : ResponseResult.FAIL();
    }
}
