package com.bosssoft.ecds.encodeserver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetBillNumDto;
import com.bosssoft.ecds.encodeserver.entity.vo.GetBillNumVo;
import com.bosssoft.ecds.encodeserver.response.CommonCode;
import com.bosssoft.ecds.encodeserver.response.QueryResponseResult;
import com.bosssoft.ecds.encodeserver.response.ResponseResult;
import com.bosssoft.ecds.encodeserver.service.GetCodeService;
import com.bosssoft.ecds.encodeserver.util.FillZeroUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 黄杰峰
 * @Date 2020/8/7 0007 15:26
 * @Description 获取财政票据号码控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/encode")
@Log4j2
@Slf4j
public class GetCodeController {

    private final GetCodeService getCodeService;

    public GetCodeController(GetCodeService getCodeService) {
        this.getCodeService = getCodeService;
    }

    /**
     * 批量获取财政号码
     * @param getBillNumVo
     * @return
     */
    @PostMapping("/batch")
    @ApiOperation(value = "批量获取票据号码", notes = "传入区划编码、分类编码、种类编码、年度编码和取号数量获取，返回票据号码段")
    public ResponseResult getBatchCode(
            @RequestBody @ApiParam(value = "获取财政号码Vo")
                    GetBillNumVo getBillNumVo) {
        // Vo转换为Dto
        GetBillNumDto getBillNumDto = new GetBillNumDto();
        BeanUtil.copyProperties(getBillNumVo, getBillNumDto);
        // 填充0
        FillZeroUtil.fillZero(getBillNumDto);
        log.info("=====获取财政编码：");
        NumSegDto batchCode = getCodeService.getBatchCode(getBillNumDto);
        log.info(batchCode);
        if (batchCode != null) {
            return new QueryResponseResult<>(CommonCode.GET_CODE_SUCCESS, batchCode);
        } else {
            return new ResponseResult(false, 500, "财政代码可能未创建，请创建后再赋码");
        }
    }
}
