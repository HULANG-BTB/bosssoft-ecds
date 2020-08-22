package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.AccBaseInfoDTO;
import com.bosssoft.ecds.entity.dto.AccBillDTO;
import com.bosssoft.ecds.entity.dto.AccIntoInfoDTO;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.vo.AccBaseInfoVO;
import com.bosssoft.ecds.entity.vo.AccBillVO;
import com.bosssoft.ecds.entity.vo.AccIntoInfoVO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.CbillAccountingService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/ecds-bill-accounting/account")
@Api(tags = "基础入帐信息交互")
@Slf4j
public class CbillAccountingController {
    @Autowired
    private CbillAccountingService cbillAccountingService;

    @PostMapping("/insertAccount")
    @ApiOperation(value = "开票阶段基础信息")
    public ResponseResult insert(@RequestBody @Validated AccBaseInfoVO accBaseInfoVO){
        AccBaseInfoDTO accBaseInfoDTO = new AccBaseInfoDTO();
        MyBeanUtil.copyProperties(accBaseInfoVO,accBaseInfoDTO);
        return cbillAccountingService.insert(accBaseInfoDTO);
    }

    @PostMapping("/getAccount")
    @ApiImplicitParam(name = "billSerialId", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "通过票据校验码查询代缴费金额")
    public ResponseResult getAccount(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingService.selectAccount(cbillAccountingDTO);
    }

    @PostMapping("/updateAccount")
    @ApiOperation(value = "缴费单位传值")
    public ResponseResult updateAccount(@RequestBody @Validated AccIntoInfoVO accIntoInfoVO){
        AccIntoInfoDTO accIntoInfoDto = new AccIntoInfoDTO();
        MyBeanUtil.copyProperties(accIntoInfoVO,accIntoInfoDto);
        return cbillAccountingService.insertAccount(accIntoInfoDto);
    }


    @PostMapping("/getStatus")
    @ApiImplicitParam(name = "checkCode", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "查询入账状态")
    public ResponseResult selectStatus(@RequestBody AccBaseInfoVO accBaseInfoVO){
        AccBaseInfoDTO accBaseInfoDTO = new AccBaseInfoDTO();
        MyBeanUtil.copyProperties(accBaseInfoVO,accBaseInfoDTO);
        return cbillAccountingService.selectStatus(accBaseInfoDTO);
    }


    @PostMapping("/updateBillInfo")
    @ApiOperation(value = "开票单位发放阶段传值")
    public ResponseResult updateBillInfo(@RequestBody @Validated AccBillVO accBillVO){
        AccBillDTO accBillDTO = new AccBillDTO();
        MyBeanUtil.copyProperties(accBillVO,accBillDTO);
        return cbillAccountingService.insertBillInfo(accBillDTO);
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除入账信息")
    public ResponseResult delete(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = MyBeanUtil.myCopyProperties(cbillAccountingVO, CbillAccountingDTO.class);
        return cbillAccountingService.delete(cbillAccountingDTO);
    }

    @PostMapping("/batchDelete")
    @ApiOperation(value = "批量删除入账信息")
    public ResponseResult batchDelete(@RequestBody List<CbillAccountingVO> cbillAccountingVOList){
        List<CbillAccountingDTO> cbillAccountingDTOList = MyBeanUtil.copyListProperties(cbillAccountingVOList, CbillAccountingDTO::new);
        return cbillAccountingService.batchDelete(cbillAccountingDTOList);
    }

    @PutMapping("/updateStatus")
    @ApiOperation(value = "更改入账状态")
    public ResponseResult updateStatus(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = MyBeanUtil.myCopyProperties(cbillAccountingVO, CbillAccountingDTO.class);
        return cbillAccountingService.updateStatus(cbillAccountingDTO);
    }

}

