package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.vo.ApplyVo;
import com.bosssoft.ecds.entity.vo.ConfirmVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.ConfirmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qiuheng
 * @create: 2020-08-21 10:26
 **/
@RestController
@RequestMapping(value = "confirm")
@CrossOrigin
@Api(description = "票据销毁审核接口")
public class ConfirmController {
    @Autowired
    private ConfirmService confirmService;

    @ApiOperation(value = "新增票据销毁审核信息", notes = "新增票据销毁审核信息")
    @ApiImplicitParam(name = "confirmVo", value = "审核VO", paramType = "query", required = true, dataType = "Object")
    @RequestMapping(value = "insertConfirmInfo", method = RequestMethod.POST)
    public ResponseResult insertConfirmInfo(@RequestBody ConfirmVo confirmVo) {
        confirmService.insertConfirmInfo(confirmVo);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
