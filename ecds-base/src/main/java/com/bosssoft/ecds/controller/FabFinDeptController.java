package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.po.FabFinDept;
import com.bosssoft.ecds.entity.vo.FabFinDeptVo;
import com.bosssoft.ecds.service.FabFinDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 归口财政部门管理
 * @author: lin.wanning
 * @date: Created in 2020/8/10 17:08
 * @modified By:
 */
@RestController
@RequestMapping("/fabFinDept")
@Api(value = "归口财政部门管理接口")
public class FabFinDeptController {
    @Autowired
    private FabFinDeptService fabFinDeptService;

    @ApiOperation(value = "保存，修改操作")
    @PostMapping("/save")
    ResponseResult saveOrUpdateFabFinDept(@RequestBody FabFinDept fabFinDept) {
        return fabFinDeptService.saveOrUpdateFabFinDept(fabFinDept);
    }

    @ApiOperation(value = "删除项目")
    @DeleteMapping("/delete")
    ResponseResult delete(@RequestBody Long id) {
        return fabFinDeptService.del(id);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/list")
    QueryResponseResult findAll(@RequestBody FabFinDeptVo fabFinDeptVo) {
        return fabFinDeptService.findAll(fabFinDeptVo);
    }

    @ApiOperation(value = "获取所有启用的归口部门信息")
    @PostMapping("/get")
    QueryResponseResult findAllWithEnable() {
        return fabFinDeptService.findAllWithEnable();
    }
}
