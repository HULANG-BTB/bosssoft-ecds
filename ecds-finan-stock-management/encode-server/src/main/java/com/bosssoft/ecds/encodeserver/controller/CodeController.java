package com.bosssoft.ecds.encodeserver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.encodeserver.entity.dto.CreateFinanceCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetFinanceNumDto;
import com.bosssoft.ecds.encodeserver.entity.vo.CreateFinanceCodeVo;
import com.bosssoft.ecds.encodeserver.entity.vo.GetFinanceNumVo;
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
public class CodeController {

    private final GetCodeService getCodeService;

    public CodeController(GetCodeService getCodeService) {
        this.getCodeService = getCodeService;
    }

    /**
     * 批量获取财政号码
     * @param getFinanceNumVo
     * @return
     */
    @PostMapping("/getBatchCode")
    @ApiOperation(value = "批量获取票据号码", notes = "传入区划编码、分类编码、种类编码、年度编码和取号数量获取，返回票据号码段")
    public ResponseResult getBatchCode(
            @RequestBody @ApiParam(value = "获取财政号码Vo")
                    GetFinanceNumVo getFinanceNumVo) {
        // Vo转换为Dto
        GetFinanceNumDto getFinanceNumDto = new GetFinanceNumDto();
        BeanUtil.copyProperties(getFinanceNumVo, getFinanceNumDto);
        // 填充0（应在前端实现）
        FillZeroUtil.fillZero(getFinanceNumDto);
        log.info("=====获取财政编码：");
        NumSegDto batchCode = getCodeService.getBatchCode(getFinanceNumDto);
        if (batchCode != null) {
            return new QueryResponseResult<>(CommonCode.addEnum("CREATE_SUCCESS",
                    true, 200, "-----获取财政代码成功"), batchCode);
        } else {
            return new ResponseResult(false, 500, "-----财政代码可能未创建，请创建后再赋码");
        }
    }

    /**
     * 财政对未使用的票据代码申请赋码操作时，需要先进行创建(?)
     *  需识别当前代码是否已经创建，避免重复创建
     * @param createFinanceCodeVo
     * @return
     */
    @PostMapping("/createCode")
    @ApiOperation(value = "创建新的票据代码", notes = "传入区划编码、分类编码、种类编码、年度编码、创建人姓名和创建人Id创建")
    public ResponseResult createCode(
            @RequestBody @ApiParam("创建财政编码Vo")
                    CreateFinanceCodeVo createFinanceCodeVo) {
        // Vo转换为Dto
        CreateFinanceCodeDto createFinanceCodeDto = new CreateFinanceCodeDto();
        BeanUtil.copyProperties(createFinanceCodeVo, createFinanceCodeDto);
        // 填充0（应在前端实现）
        FillZeroUtil.fillZero(createFinanceCodeDto);
        // 使用Redis作为仓库，有必要在日志文件中记录每次存取事件
        log.info("=====创建新的财政编码：" + createFinanceCodeDto.financeCode());
        boolean createdFlag = getCodeService.createNewCode(createFinanceCodeDto);
        log.info(createdFlag ? "-----创建成功" : "-----创建失败，该财政编码已存在");
        log.info("创建者：" + createFinanceCodeDto.getFOperator() + ", 创建者Id："
                + createFinanceCodeDto.getFOperatorId());
        if (createdFlag) {
            return new ResponseResult(true, 200, "创建成功");
        } else {
            return new ResponseResult(false, 500, "已创建，请勿重复创建");
        }
    }
}
