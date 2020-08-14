package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AccBaseInfoDTO;
import com.bosssoft.ecds.entity.dto.AccBillDTO;
import com.bosssoft.ecds.entity.dto.AccIntoInfoDTO;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.entity.vo.AccBaseInfoVO;
import com.bosssoft.ecds.entity.vo.AccBillVO;
import com.bosssoft.ecds.entity.vo.AccIntoInfoVO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.service.CbillAccountingService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult listAll(){
        return cbillAccountingService.listAll();
    }

    @RequestMapping("/listByPage")
    @ApiOperation(value = "分页查询")
    public QueryResponseResult<PageVO> insertBillInfo(@RequestBody PageVO pageVO){
        PageDTO<CbillAccountingPO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return cbillAccountingService.listByPage(pageDTO);
    }

    @RequestMapping("/getByCheckCode")
    @ApiImplicitParam(name = "billSerialId", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "通过票据校验码查询入账基础信息")
    public ResponseResult getByCheckCode(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingService.selectBySerialId(cbillAccountingDTO);
    }

    @RequestMapping("/getByBillId")
    @ApiImplicitParam(name = "billNo", value = "票号", dataType = "String")
    @ApiOperation(value = "通过票号查询入账基础信息")
    public ResponseResult getByBillId(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingService.selectByBillId(cbillAccountingDTO);
    }

    @RequestMapping("/getByAgenIdcode")
    @ApiImplicitParam(name = "agenIdcode", value = "单位代码", dataType = "String")
    @ApiOperation(value = "通过单位代码查询某单位的所有入账基础信息")
    public ResponseResult getByAgenIdcode(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingService.selectByAgenIdcode(cbillAccountingDTO);
    }

    @RequestMapping("/insertAccountBaseInfo")
    @ApiOperation(value = "开票阶段基础信息")
    public ResponseResult insertAccountBaseInfo(@RequestBody AccBaseInfoVO accBaseInfoVO){
        AccBaseInfoDTO accBaseInfoDTO = new AccBaseInfoDTO();
        MyBeanUtil.copyProperties(accBaseInfoVO,accBaseInfoDTO);
        return cbillAccountingService.insertAccBaseInfo(accBaseInfoDTO);
    }

    @RequestMapping("/getAccount")
    @ApiImplicitParam(name = "billSerialId", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "通过票据校验码查询代缴费金额")
    public ResponseResult getAccount(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingService.selectAccount(cbillAccountingDTO);

    }

    @RequestMapping("/insertAccount")
    @ApiOperation(value = "缴费单位传值")
    public ResponseResult insertAccount(@RequestBody AccIntoInfoVO accIntoInfoVO){
        AccIntoInfoDTO accIntoInfoDto = new AccIntoInfoDTO();
        MyBeanUtil.copyProperties(accIntoInfoVO,accIntoInfoDto);
        return cbillAccountingService.insertAccount(accIntoInfoDto);
    }

    @RequestMapping("/selectStatus")
    @ApiImplicitParam(name = "checkCode", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "查询入账状态")
    public ResponseResult selectStatus(@RequestBody AccBaseInfoVO accBaseInfoVO){
        AccBaseInfoDTO accBaseInfoDTO = new AccBaseInfoDTO();
        MyBeanUtil.copyProperties(accBaseInfoVO,accBaseInfoDTO);
        return cbillAccountingService.selectStatus(accBaseInfoDTO);

    }

    @RequestMapping("/insertBillInfo")
    @ApiOperation(value = "开票单位发放阶段传值")
    public ResponseResult insertBillInfo(@RequestBody AccBillVO accBillVO){
        AccBillDTO accBillDTO = new AccBillDTO();
        MyBeanUtil.copyProperties(accBillVO,accBillDTO);
        return cbillAccountingService.insertBillInfo(accBillDTO);
    }

//    @RequestMapping("/delete")
//    @ApiOperation(value = "开票单位发放阶段传值")
//    public ResponseResult insertBillInfo(@RequestBody AccBillVO accBillVO){
//        AccBillDTO accBillDTO = new AccBillDTO();
//        MyBeanUtil.copyProperties(accBillVO,accBillDTO);
//        return cbillAccountingService.insertBillInfo(accBillDTO);
//    }
//
//    @RequestMapping("/")
//    @ApiOperation(value = "开票单位发放阶段传值")
//    public ResponseResult insertBillInfo(@RequestBody AccBillVO accBillVO){
//        AccBillDTO accBillDTO = new AccBillDTO();
//        MyBeanUtil.copyProperties(accBillVO,accBillDTO);
//        return cbillAccountingService.insertBillInfo(accBillDTO);
//    }

}

