package com.bosssoft.ecds.template.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.vo.PrintTemplateVo;
import com.bosssoft.ecds.template.service.HtmlService;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.util.BeanCopyUtil;
import com.bosssoft.ecds.template.util.response.CommonCode;
import com.bosssoft.ecds.template.util.response.QueryResponseResult;
import com.bosssoft.ecds.template.util.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    HtmlService htmlService;

    /**
     * 打印模板列表
     */
    @ApiOperation("列出所有的打印模板")
    @GetMapping("/list")
    public ResponseResult listTemplate() {
        List<PrintTemplateDto> templateDTOs = printTemplateService.listAll();
        List<PrintTemplateVo> templateVOs =
                BeanCopyUtil.copyListProperties(templateDTOs, PrintTemplateVo::new);
        return new QueryResponseResult<>(CommonCode.SUCCESS, templateVOs);
    }

    /**
     * 新建打印模板，上传模板文件
     *
     * @param templateName 打印模板名字
     * @param billCode     票据代码（前6位）
     * @param memo         备注
     * @param file         文件
     * @return 是否创建成功
     */
    @ApiOperation("新建打印模板，上传模板文件")
    @PostMapping("/uploadTemplate")
    public ResponseResult uploadTemplate(
            @RequestParam @ApiParam("模板名字") String templateName,
            @RequestParam @ApiParam("票据代码（前6位，不包含年度）") String billCode,
            @RequestParam @ApiParam("备注") String memo,
            @RequestParam("file") @ApiParam("文件") MultipartFile file
    ) throws IOException {

        if (billCode.length() != 6) {
            return new ResponseResult(CommonCode.INVLIDATE);
        }

        String content = new String(file.getBytes());
        PrintTemplateDto templateDTO = new PrintTemplateDto();
        templateDTO.setRgnCode(billCode.substring(0, 2));
        templateDTO.setTypeId(billCode.substring(2, 4));
        templateDTO.setSortId(billCode.substring(4, 6));
        templateDTO.setName(templateName);
        templateDTO.setMemo(memo);
        templateDTO.setTemplate(content);

        printTemplateService.add(templateDTO);

        return ResponseResult.SUCCESS();
    }

    /**
     * 删除一个打印模板
     *
     * @param id 模板主键
     * @return 是否删除成功
     */
    @ApiOperation("删除一个打印模板")
    @DeleteMapping("/remove/{id}")
    public ResponseResult removeTemplate(@PathVariable Long id) {
        boolean success = printTemplateService.remove(id);
        return success ? ResponseResult.SUCCESS() : ResponseResult.FAIL();
    }

    /**
     * 根据模板id获取模板样板，即返回空的票样
     *
     * @param id 模板主键
     * @return 空白票据html
     */
    @ApiOperation("根据模板id获取模板票样")
    @GetMapping(value = "/content/{id}.html", produces = MediaType.TEXT_HTML_VALUE)
    public byte[] getHtml(@PathVariable Long id) {
        PrintTemplateDto templateDTO = printTemplateService.getDtoById(id);
        // 渲染空白票据
        NontaxBillDto billDTO = new NontaxBillDto();
        billDTO.setItems(new ArrayList<>());
        String htmlTemplate = htmlService.genBillHtml(billDTO, templateDTO.getTemplate());
        return htmlTemplate.getBytes();
    }

    /**
     * 根据模板id下载模板文件
     *
     * @param id 模板主键
     * @return 票据模板ftl文件
     */
    @ApiOperation("根据模板id下载模板文件")
    @GetMapping(value = "/content/{id}.ftl", produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] getTemplate(@PathVariable Long id) {
        PrintTemplateDto templateDTO = printTemplateService.getDtoById(id);
        return templateDTO.getTemplate().getBytes();
    }

    /**
     * 模板列表分页查询
     *
     * @param current   第几页
     * @param size 每页多少项
     */
    @ApiOperation("模板列表分页查询")
    @GetMapping("/listPage")
    public ResponseResult listPage(
            @RequestParam
            @ApiParam(value = "页码", example = "1")
                    Long current,
            @RequestParam(defaultValue = "10", required = false)
            @ApiParam(value = "每页几项", example = "10")
                    Long size
    ) {
        IPage<PrintTemplateVo> page = printTemplateService.getPageVO(new Page<>(current, size));
        return new QueryResponseResult<>(CommonCode.SUCCESS, page);
    }
}
