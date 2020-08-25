package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.vo.AddRegionVO;
import com.bosssoft.ecds.entity.vo.EditRegionVO;
import com.bosssoft.ecds.entity.vo.QueryRegionRequestVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: lpb
 * @create: 2020-08-05 11:30
 */
@RestController
@RequestMapping("/region")
@Api(value = "区划信息管理",description = "区划信息管理" )
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("/category")
    @ApiOperation(value = "获取三级区划信息")
    public QueryResponseResult getAllRegion(){
        return regionService.getAllRegion();
    }

    @GetMapping("/province")
    @ApiOperation(value = "获取省级区划分类信息")
    public QueryResponseResult getProvinceCategory(){
        return regionService.getProvinceCategory();
    }

    @GetMapping("/city")
    @ApiOperation(value = "获取省级区划分类信息")
    public QueryResponseResult getCityCategory(){
        return regionService.getCityCategory();
    }

    @GetMapping("/list/{page}/{size}")
    @ApiOperation(value = "根据查询参数获取分页区划信息")
    public QueryResponseResult list(@PathVariable("page") Integer page, @PathVariable("size") Integer size, QueryRegionRequestVO queryRegionRequest){
        return regionService.list(page,size,queryRegionRequest);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加新的区划信息")
    public ResponseResult add(@RequestBody AddRegionVO addRegion){
        return regionService.add(addRegion);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新区划信息")
    public ResponseResult edit(@RequestBody EditRegionVO editRegion){
        return regionService.edit(editRegion);
    }

    @DeleteMapping("/delete/{rid}")
    @ApiOperation(value = "删除指定ID区划信息")
    public ResponseResult delete(@PathVariable("rid")Long id){
        return regionService.delete(id);
    }

    @GetMapping("/getGrandId/{parentId}")
    @ApiOperation(value = "获取祖父节点ID")
    public QueryResponseResult getGrandId(@PathVariable("parentId") Long pid){
        return regionService.getGrandId(pid);
    }

    @GetMapping("/getBusIds/{busParentId}")
    @ApiOperation(value = "获取当前节点父级以上节点ID")
    public QueryResponseResult getBusIds(@PathVariable("busParentId") Long pid){
        return regionService.getBusIds(pid);
    }

}
