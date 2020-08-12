package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.AccBaseInfoDTO;
import com.bosssoft.ecds.entity.dto.AccBillDTO;
import com.bosssoft.ecds.entity.dto.AccIntoInfoDTO;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.vo.AccBaseInfoVO;
import com.bosssoft.ecds.entity.vo.AccBillVO;
import com.bosssoft.ecds.entity.vo.AccIntoInfoVO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.service.CbillAccountingService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/api/account")
@Api(tags = "基础入帐")
@Slf4j
public class CbillAccountingController {
    @Autowired
    private CbillAccountingService cbillAccountingService;

    @RequestMapping("/listAll")
    @ResponseBody
    @ApiOperation(value = "入账基础信息列表")
    public String listAll(){
        List<CbillAccountingDTO> list = cbillAccountingService.listAll();
        List<CbillAccountingVO> cbillAccountingVOList = MyBeanUtil.copyListProperties(list, CbillAccountingVO.class);
        return ResponseUtils.getResponse(cbillAccountingVOList, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/getByCheckCode")
    @ApiImplicitParam(name = "billSerialId", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "通过票据校验码查询入账基础信息")
    public String getByCheckCode(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        cbillAccountingDTO = cbillAccountingService.selectBySerialId(cbillAccountingDTO);
        MyBeanUtil.copyProperties(cbillAccountingDTO,cbillAccountingVO);
        return ResponseUtils.getResponse(cbillAccountingVO, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/getByBillId")
    @ApiImplicitParam(name = "billNo", value = "票号", dataType = "String")
    @ApiOperation(value = "通过票号查询入账基础信息")
    public String getByBillId(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        cbillAccountingDTO = cbillAccountingService.selectByBillId(cbillAccountingDTO);
        MyBeanUtil.copyProperties(cbillAccountingDTO,cbillAccountingVO);
        return ResponseUtils.getResponse(cbillAccountingVO, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/getByAgenIdcode")
    @ApiImplicitParam(name = "agenIdcode", value = "单位代码", dataType = "String")
    @ApiOperation(value = "通过单位代码查询某单位的所有入账基础信息")
    public String getByAgenId(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        List<CbillAccountingDTO> accountingDTOList = cbillAccountingService.selectByAgenIdcode(cbillAccountingDTO);
        return ResponseUtils.getResponse(accountingDTOList, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/insertAccountBaseInfo")
    @ApiOperation(value = "开票阶段基础信息")
    public String insertAccountBaseInfo(@RequestBody AccBaseInfoVO accBaseInfoVO){
        AccBaseInfoDTO accBaseInfoDTO = new AccBaseInfoDTO();
        MyBeanUtil.copyProperties(accBaseInfoVO,accBaseInfoDTO);
        Boolean result = cbillAccountingService.insertAccBaseInfo(accBaseInfoDTO);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/getAccount")
    @ApiImplicitParam(name = "billSerialId", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "通过票据校验码查询代缴费金额")
    public String getAccount(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        cbillAccountingDTO = cbillAccountingService.selectAccount(cbillAccountingDTO);
        MyBeanUtil.copyProperties(cbillAccountingDTO,cbillAccountingVO);
        return ResponseUtils.getResponse(cbillAccountingVO, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/insertAccount")
    @ApiOperation(value = "缴费单位传值")
    public String insertAccount(@RequestBody AccIntoInfoVO accIntoInfoVO){
        AccIntoInfoDTO accIntoInfoDto = new AccIntoInfoDTO();
        MyBeanUtil.copyProperties(accIntoInfoVO,accIntoInfoDto);
        boolean result = cbillAccountingService.insertAccount(accIntoInfoDto);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/selectStatus")
    @ApiImplicitParam(name = "checkCode", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "查询入账状态")
    public String selectStatus(@RequestBody AccBaseInfoVO accBaseInfoVO){
        AccBaseInfoDTO accBaseInfoDTO = new AccBaseInfoDTO();
        MyBeanUtil.copyProperties(accBaseInfoVO,accBaseInfoDTO);
        boolean result = cbillAccountingService.selectStatus(accBaseInfoDTO);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    @RequestMapping("/insertBillInfo")
    @ApiOperation(value = "开票单位发放阶段传值")
    public String insertBillInfo(@RequestBody AccBillVO accBillVO){
        AccBillDTO accBillDTO = new AccBillDTO();
        MyBeanUtil.copyProperties(accBillVO,accBillDTO);
        boolean result = cbillAccountingService.insertBillInfo(accBillDTO);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

}

