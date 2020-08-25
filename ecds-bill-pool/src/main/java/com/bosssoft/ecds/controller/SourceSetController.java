package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.SourceSetDto;
import com.bosssoft.ecds.entity.vo.SourceSetVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.SourceSetService;
import com.bosssoft.ecds.utils.BeanUtils;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@Api
@RestController
@RequestMapping("/pool")
public class SourceSetController {

    private static final Logger logger = LoggerFactory.getLogger(SourceSetController.class);

    @Resource
    SourceSetService sourceSetService;

    @PostMapping("/setSource")
    public ResponseResult setSource(@RequestBody @Valid SourceSetDto sourceSetDto) {
        return sourceSetService.updateSet(sourceSetDto) > 0 ?
                new ResponseResult(true, 10000, "设置票据池成功") :
//                new ResponseResult(CommonCode.SUCCESS):
                new ResponseResult(CommonCode.SERVER_ERROR);
    }

    @PostMapping("/addSource")
    public ResponseResult addSource(@RequestBody @Valid SourceSetDto sourceSetDto) {
        return sourceSetService.updateSet(sourceSetDto) > 0 ?
                new ResponseResult(true, 10000, "创建票据池成功") :
//                new ResponseResult(CommonCode.SUCCESS):
                new ResponseResult(CommonCode.SERVER_ERROR);
    }

    @PostMapping("/removeSource")
    public ResponseResult removeSource(@RequestBody @Valid SourceSetDto sourceSetDto) {
        return sourceSetService.updateSet(sourceSetDto) > 0 ?
                new ResponseResult(true, 10000, "修改票据池状态成功") :
//                new ResponseResult(CommonCode.SUCCESS):
                new ResponseResult(CommonCode.SERVER_ERROR);
    }

    /**
     * 获取
     *
     * @return
     */
    @GetMapping("/retrieveSetList")
    public ResponseResult retrieveSetList() {
        List<SourceSetVo> list = BeanUtils.convertList(sourceSetService.retrieveSetList(), SourceSetVo.class);
        return list != null ?
                new QueryResponseResult<>(CommonCode.SUCCESS, list) :
                new ResponseResult(CommonCode.FAIL);

    }

    @RequestMapping(value = "/retrieveSetByCode", method = RequestMethod.GET)
    public ResponseResult retrieveSetByCode(@RequestParam @Valid @Length(min = 8, max = 8, message = "票号编码不规范") String billTypeCode) {
        SourceSetVo sourceSetVo = BeanUtils.convertObject(sourceSetService.retrieveSetByCode(billTypeCode), SourceSetVo.class);
        return sourceSetVo != null ?
                new QueryResponseResult<>(CommonCode.SUCCESS, sourceSetVo) :
                new ResponseResult(CommonCode.FAIL);
    }
}
