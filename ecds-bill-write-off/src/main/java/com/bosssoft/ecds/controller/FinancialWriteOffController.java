package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffReceiveDTO;
import com.bosssoft.ecds.entity.dto.WriteOffResultDTO;
import com.bosssoft.ecds.entity.vo.*;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import com.bosssoft.ecds.service.MonitorRecordService;
import com.bosssoft.ecds.service.UnitWriteOffService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 财务段的核销服务
 */
@RestController
@RequestMapping("/financial")
@CrossOrigin
public class FinancialWriteOffController {

    @Autowired
    private FinancialWriteOffService financialWriteOffService;

    @Autowired
    private MonitorRecordService monitorRecordService;

    @Autowired
    private UnitWriteOffService unitWriteOffService;
    /**
     * 获取单位端传来的核销信息
     * 接收一段时间的下级单位传来的核销信息
     *
     * @param writeOffReceiveUnitInfoVO
     * @return java.util.List
     */
    @ResponseBody
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ApiOperation(value = "获取核销请求", notes = "根据单位Id获取该单位上报的核销请求")
    @ApiImplicitParam(name = "writeOffReceiveUnitInfoVO", dataType = "WriteOffReceiveUnitInfoVO", value = "需要核销的单位信息")
    public QueryResponseResult receive(@RequestBody WriteOffReceiveUnitInfoVO writeOffReceiveUnitInfoVO) {
        // 这里DTO要修改成VO， 为了方便测试所以没改，以下的一样
        List<WriteOffReceiveVO> list = new ArrayList<>();
        for (WriteOffReceiveDTO writeOffReceiveDTO : financialWriteOffService.receive(writeOffReceiveUnitInfoVO.getfAgenIdCode())){
            WriteOffReceiveVO writeOffReceiveVO = new WriteOffReceiveVO();
            writeOffReceiveVO.setNo(writeOffReceiveDTO.getFNo());
            writeOffReceiveVO.setDate(writeOffReceiveDTO.getFDate());
            writeOffReceiveVO.setReason(writeOffReceiveDTO.getFMemo());
            writeOffReceiveVO.setAuthor(writeOffReceiveDTO.getFAuthor());
            list.add(writeOffReceiveVO);
        }
        return new QueryResponseResult(CommonCode.SUCCESS, list);
    }

    /**
     * 退回单位端传来的核销信息
     * 需要退回的核销信息根据单位ID回到单位端进行修改
     *
     * @param writeOffReceiveVOList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendBack", method = RequestMethod.POST)
    @ApiOperation(value = "退还核销请求", notes = "根据需要将核销请求退还给单位段")
    @ApiImplicitParam(name = "writeOffReceiveVOList", dataType = "List", value = "退还的核销请求信息")
    public ResponseResult sendBack(@RequestBody List<WriteOffReceiveVO> writeOffReceiveVOList) {
        List<WriteOffReceiveDTO> list = new ArrayList<>(writeOffReceiveVOList.size());
        for (WriteOffReceiveVO writeOffReceiveVO : writeOffReceiveVOList){
            WriteOffReceiveDTO writeOffReceiveDTO = new WriteOffReceiveDTO();
            writeOffReceiveDTO.setFNo(writeOffReceiveVO.getNo());
            writeOffReceiveDTO.setFDate(writeOffReceiveVO.getDate());
            writeOffReceiveDTO.setFMemo(writeOffReceiveVO.getReason());
            writeOffReceiveDTO.setFAuthor(writeOffReceiveVO.getAuthor());
            list.add(writeOffReceiveDTO);
        }
        financialWriteOffService.sendBack(list);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 获取核销信息详情
     *
     * @param writeOffDetailRequestVO
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/getDetails", method = RequestMethod.POST)
    @ApiOperation(value = "获取核销详情", notes = "根据核销单号获取核销详情")
    @ApiImplicitParam(name = "writeOffDetailRequestVO", dataType = "WriteOffDetailRequestVO", value = "核销详情的请求")
    public QueryResponseResult getDetails(@RequestBody WriteOffDetailRequestVO writeOffDetailRequestVO) {
        // 根据业务单号获取详细
        WriteOffDetailDTO writeOffDetailDTO = financialWriteOffService.getDetail(writeOffDetailRequestVO.getfNo());
        WriteOffDetailVO writeOffDetailVO = new WriteOffDetailVO();
        writeOffDetailVO.setWriteOffBillInvDetailDTOList(writeOffDetailDTO.getWriteOffBillInvDetailDTOList());
        writeOffDetailVO.setWriteOffIncomeDetailDTOList(writeOffDetailDTO.getWriteOffIncomeDetailDTOList());
        writeOffDetailVO.setWriteOffInvoceDetailDTOList(writeOffDetailDTO.getWriteOffInvoceDetailDTOList());
        writeOffDetailVO.setWriteOffMonitorDetailDTOList(monitorRecordService.getMonitorDetail(writeOffDetailDTO));
        return new QueryResponseResult(CommonCode.SUCCESS, writeOffDetailVO);
    }

    /**
     * 通过审核
     * 存储数据
     *
     * @param writeOffResultVO
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @ApiOperation(value = "审核通过", notes = "审核通过")
    @ApiImplicitParam(name = "writeOffResultVO", dataType = "WriteOffResultVO", value = "审核结果")
    public ResponseResult setResult(@RequestBody WriteOffResultVO writeOffResultVO){
        System.out.println(writeOffResultVO.getRes());
        WriteOffResultDTO resultDTO = new WriteOffResultDTO();
        resultDTO.setFAgenIdCode(writeOffResultVO.getfAgenIdCode());
        resultDTO.setFNo(writeOffResultVO.getfNo());
        resultDTO.setRes(writeOffResultVO.getRes());
        financialWriteOffService.setResult(resultDTO);
        return new ResponseResult(CommonCode.SUCCESS);
    }

}
