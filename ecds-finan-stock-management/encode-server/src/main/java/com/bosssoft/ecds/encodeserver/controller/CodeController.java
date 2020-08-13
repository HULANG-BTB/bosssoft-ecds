package com.bosssoft.ecds.encodeserver.controller;

import com.bosssoft.ecds.encodeserver.entity.dto.CreateFinanceCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;
import com.bosssoft.ecds.encodeserver.util.EncodeResult;
import com.bosssoft.ecds.encodeserver.service.GetCodeService;
import com.bosssoft.ecds.encodeserver.util.ResponseUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 黄杰峰
 * @Date 2020/8/7 0007 15:26
 * @Description 获取财政票据号码控制器
 */
@RestController
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
    public EncodeResult getBatchCode(@RequestBody GetFinanceNumDto getFinanceNumDto) {
        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDto);
        if (batchCode != null) {
            return EncodeResult.getEncodeResult(200, "赋码成功", batchCode);
        } else {
            return EncodeResult.getEncodeResult(500, "财政代码未创建，请创建后再赋码");
        }
    }

    @PostMapping("/test")
    public String test(@RequestBody GetFinanceNumDto getFinanceNumDto) {
        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDto);
        if (batchCode != null) {
            return ResponseUtils.getResponse(200, "赋码成功", batchCode);
        } else {
            return ResponseUtils.getResponse(500, "财政代码未创建，请创建后再赋码");
        }
    }

    /**
     * 财政对未使用的票据代码申请赋码操作时，需要先进行创建(?)
     *  需识别当前代码是否已经创建，避免重复创建
     * @param createFinanceCodeDto
     * @return
     */
    @PostMapping("/createCode")
    public EncodeResult createCode(@RequestBody CreateFinanceCodeDto createFinanceCodeDto) {
        boolean createdFlag = getCodeService.createNewCode(createFinanceCodeDto);
        if (createdFlag) {
            return EncodeResult.getEncodeResult(200, "财政代码创建成功");
        } else {
            return EncodeResult.getEncodeResult(500, "财政代码创建失败，该代码已创建，请勿重复创建");
        }
    }
}
