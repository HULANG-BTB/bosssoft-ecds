package com.bosssoft.ecds.encodeserver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.encodeserver.entity.dto.CreateBatchBillCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.CreateBillCodeDto;
import com.bosssoft.ecds.encodeserver.entity.vo.CreateBatchBillCodeVo;
import com.bosssoft.ecds.encodeserver.entity.vo.CreateBillCodeVo;
import com.bosssoft.ecds.encodeserver.response.ResponseResult;
import com.bosssoft.ecds.encodeserver.service.CreateCodeService;
import com.bosssoft.ecds.encodeserver.util.FillZeroUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 黄杰峰
 * @Date 2020/8/12 0018 8:50
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/create")
public class CreateCodeController {

    private final CreateCodeService createCodeService;

    public CreateCodeController(CreateCodeService createCodeService) {
        this.createCodeService = createCodeService;
    }

    /**
     * 财政对未使用的票据代码申请赋码操作时，需要先进行创建
     *  需识别当前代码是否已经创建，避免重复创建
     * @param createBillCodeVo
     * @return
     */
    @PostMapping("/single")
    @ApiOperation(value = "创建新的票据代码", notes = "传入区划编码、分类编码、种类编码、年度编码、创建人姓名和创建人Id创建")
    public ResponseResult createCode(
            @RequestBody @ApiParam("创建财政编码Vo") CreateBillCodeVo createBillCodeVo) {
        // Vo转换为Dto
        CreateBillCodeDto createBillCodeDto = new CreateBillCodeDto();
        BeanUtil.copyProperties(createBillCodeVo, createBillCodeDto);
        // 填充0
        FillZeroUtil.fillZero(createBillCodeDto);
        boolean createdFlag = createCodeService.createSingleCode(createBillCodeDto);
        if (createdFlag) {
            return new ResponseResult(true, 200, "创建成功");
        } else {
            return new ResponseResult(false, 500, "已创建，请勿重复创建");
        }
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量创建新的票据代码", notes = "传入区划编码、分类编码、种类编码起始及末尾、年度编码、创建人和创建人Id进行创建，" +
            "适合在年末时批量创建第二年的代码。")
    public ResponseResult createBatchCode(
            @RequestBody @ApiParam("批量创建财政编码Vo")CreateBatchBillCodeVo createBatchBillCodeVo) {

        // Vo转Dto
        CreateBatchBillCodeDto createBatchBillCodeDto = new CreateBatchBillCodeDto();
        BeanUtil.copyProperties(createBatchBillCodeVo, createBatchBillCodeDto);

        boolean createdFlag = createCodeService.createBatchCode(createBatchBillCodeDto);
        if (createdFlag) {
            return new ResponseResult(true, 200, "创建成功");
        } else {
            return new ResponseResult(false, 500, "创建失败，部分代码已创建");
        }
    }
}
