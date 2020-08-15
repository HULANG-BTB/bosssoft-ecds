package com.bosssoft.ecds.encodeserver.controller;

import com.bosssoft.ecds.encodeserver.entity.dto.CreateFinanceCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;
import com.bosssoft.ecds.encodeserver.util.EncodeResult;
import com.bosssoft.ecds.encodeserver.service.GetCodeService;
import com.bosssoft.ecds.encodeserver.util.ResponseUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 黄杰峰
 * @Date 2020/8/7 0007 15:26
 * @Description 获取财政票据号码控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/encode")
public class CodeController {

    private final GetCodeService getCodeService;

    public CodeController(GetCodeService getCodeService) {
        this.getCodeService = getCodeService;
    }

    /**
     * 批量获取财政号码
     * @param getFinanceNumDto
     * @return
     */
    @PostMapping("/getBatchCode")
    @ApiOperation("批量获取票据号码")
    public EncodeResult getBatchCode(
            @RequestBody @ApiParam("获取财政号码Dto，包含区划编码、分类编码、种类编码、年度编码和取号数量")
                    GetFinanceNumDto getFinanceNumDto) {
        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDto);
        if (batchCode != null) {
            return EncodeResult.getEncodeResult(200, "赋码成功", batchCode);
        } else {
            return EncodeResult.getEncodeResult(500, "财政代码未创建，请创建后再赋码");
        }
    }

    /**
     * 财政对未使用的票据代码申请赋码操作时，需要先进行创建(?)
     *  需识别当前代码是否已经创建，避免重复创建
     * @param createFinanceCodeDto
     * @return
     */
    @PostMapping("/createCode")
    @ApiOperation("创建新的票据代码")
    public EncodeResult createCode(
            @RequestBody @ApiParam("创建财政编码Dto，包含区划编码、分类编码、种类编码、年度编码、创建人姓名和创建人Id")
                    CreateFinanceCodeDto createFinanceCodeDto) {
        boolean createdFlag = getCodeService.createNewCode(createFinanceCodeDto);
        if (createdFlag) {
            return EncodeResult.getEncodeResult(200, "财政代码创建成功");
        } else {
            return EncodeResult.getEncodeResult(500, "财政代码创建失败，该代码已创建，请勿重复创建");
        }
    }
}
