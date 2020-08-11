package com.bosssoft.ecds.encodeserver.controller;

import com.bosssoft.ecds.encodeserver.entity.dto.CreateFinanceCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;
import com.bosssoft.ecds.encodeserver.entity.vo.RestResult;
import com.bosssoft.ecds.encodeserver.service.GetCodeService;
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
    public RestResult getBatchCode(@RequestBody GetFinanceNumDto getFinanceNumDto) {
        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDto);
        return RestResult.success(batchCode);
    }

    @PostMapping("/createCode")
    public RestResult createCode(@RequestBody CreateFinanceCodeDto createFinanceCodeDto) {
        boolean createdFlag = getCodeService.createNewCode(createFinanceCodeDto);
        return RestResult.success(createdFlag);
    }
}
