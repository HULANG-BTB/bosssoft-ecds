package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.entity.po.ItemPo;
import com.bosssoft.ecds.entity.vo.ApplyVo;
import com.bosssoft.ecds.entity.vo.ResultVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.ApplyService;
import com.bosssoft.ecds.service.ItemService;
import com.bosssoft.ecds.service.feign.Test;
import com.bosssoft.ecds.service.feign.TestFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: qiuheng
 * @create: 2020-08-12 15:22
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "apply")
@Api(description = "票据销毁申请接口")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "根据主键获取票据销毁申请主信息", notes = "根据主键获取票据销毁申请主信息")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "selectById",method = RequestMethod.GET)
    public QueryResponseResult selectById(Long id) {
        return new QueryResponseResult(CommonCode.SUCCESS,applyService.selectById(id));
    }


    @ApiOperation(value = "新增票据销毁申请", notes = "新增票据销毁申请")
    @ApiImplicitParam(name = "applyVo", value = "申请VO", paramType = "query", required = true, dataType = "Object")
    @RequestMapping(value = "insertApplyInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult insertApplyInfo(@RequestBody ApplyVo applyVo) {
        applyService.insertApplyInfo(applyVo.getApplyDto());
        itemService.insertItemInfo(applyVo.getItemDtoList(), applyVo.getApplyDto().getfDestroyNo());
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "获取所有单位票据销毁申请列表", notes = "获取所有单位票据销毁申请列表")
    @RequestMapping(value = "getApplyList", method = RequestMethod.GET)
    @ResponseBody
    public QueryResponseResult getApplyList(){
        return new QueryResponseResult(CommonCode.SUCCESS,applyService.getApplyList());
    }

    @ApiOperation(value = "根据单位ID获取该单位票据销毁申请列表", notes = "根据单位ID获取该单位票据销毁申请列表")
    @ApiImplicitParam(name = "agenIdCode", value = "单位ID", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "getApplyListByAgenIdCode",method = RequestMethod.GET)
    @ResponseBody
    public QueryResponseResult getApplyListByAgenIdCode(String agenIdCode){
        return new QueryResponseResult(CommonCode.SUCCESS,applyService.getApplyListByAgenIdCode(agenIdCode));
    }

    @ApiOperation(value = "根据票据销毁申请ID查询申请主信息", notes = "根据票据销毁申请ID查询申请主信息")
    @ApiImplicitParam(name = "fDestroyNo", value = "票据销毁申请ID", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "getApplyInfoByDestroyNo", method = RequestMethod.GET)
    @ResponseBody
    public QueryResponseResult getApplyInfoByDestroyNo(String fDestroyNo){
        return new QueryResponseResult(CommonCode.SUCCESS,applyService.getApplyInfoByDestroyNo(fDestroyNo));
    }

    @ApiOperation(value = "根据票据销毁申请ID查询申请明细信息",notes = "根据票据销毁申请ID查询申请明细信息")
    @ApiImplicitParam(name = "fDestroyNo", value = "票据销毁申请ID", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "getItemListByDestroyNo",method = RequestMethod.GET)
    @ResponseBody
    public QueryResponseResult getItemListByDestroyNo(String fDestroyNo) {
        return new QueryResponseResult(CommonCode.SUCCESS,itemService.getItemListByDestroyNo(fDestroyNo));
    }

    @ApiOperation(value = "根据票据销毁申请ID删除申请主信息",notes = "根据票据销毁申请ID删除申请主信息")
    @ApiImplicitParam(name = "fDestroyNo", value = "票据销毁申请ID", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "deleteApplyInfoByDestroyNo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult deleteApplyInfoByDestroyNo(String fDestroyNo) {
        applyService.deleteApplyInfoByDestroyNo(fDestroyNo);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "根据票据销毁申请ID删除申请明细信息",notes = "根据票据销毁申请ID删除申请明细信息")
    @ApiImplicitParam(name = "fDestroyNo", value = "票据销毁申请ID", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "deleteItemInfoByDestroyNo",method = RequestMethod.GET)
    public ResponseResult deleteItemInfoByDestroyNo(String fDestroyNo) {
        itemService.deleteItemInfoByDestroyNo(fDestroyNo);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "更新票据销毁申请状态",notes = "更新票据销毁申请状态")
    @ApiImplicitParam(name = "status", value = "票据销毁审核状态", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "updateApplyInfo",method = RequestMethod.POST)
    public ResponseResult updateApplyInfo(@RequestBody ResultVo resultVo){
        applyService.updateApplyInfo(resultVo.getfDestroyNo(), resultVo.getfStatus());
        return new ResponseResult(CommonCode.SUCCESS);
    }

    }
