package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.CbillAccountingQueryService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/ecds-bill-accounting/accountQuery")
@Api(tags = "基础入帐信息查询")
@Slf4j
public class CbillAccountingQueryController {
    @Autowired
    private CbillAccountingQueryService cbillAccountingQueryService;

    @GetMapping("/listAll")
    @ResponseBody
    @ApiOperation(value = "入账基础信息列表(单纯为练前端功能，不必要集成测试)")
    public ResponseResult listAll(){
        return cbillAccountingQueryService.listAll();
    }

    @PostMapping("/listByPage")
    @ApiOperation(value = "分页查询")
    public QueryResponseResult<PageVO> listByPage(@RequestBody @Validated PageVO pageVO){
        PageDTO<CbillAccountingDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return cbillAccountingQueryService.listByPage(pageDTO);
    }

    @PostMapping("/getByCheckCode")
    @ApiImplicitParam(name = "billSerialId", value = "票据校验码", dataType = "String")
    @ApiOperation(value = "通过票据校验码查询入账基础信息")
    public ResponseResult getByCheckCode(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingQueryService.selectBySerialId(cbillAccountingDTO);
    }

    @PostMapping("/getByBillId")
    @ApiImplicitParam(name = "billNo", value = "票号", dataType = "String")
    @ApiOperation(value = "通过票号查询入账基础信息")
    public ResponseResult getByBillId(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingQueryService.selectByBillId(cbillAccountingDTO);
    }

    @PostMapping("/getByAgenIdcode")
    @ApiImplicitParam(name = "agenIdcode", value = "单位代码", dataType = "String")
    @ApiOperation(value = "通过单位代码查询某单位的所有入账基础信息")
    public ResponseResult getByAgenIdcode(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingQueryService.selectByAgenIdcode(cbillAccountingDTO);
    }

    @PostMapping("/updateBill")
    @ApiOperation(value = "更新单条信息(单纯为练前端功能，不必要集成测试)")
    public ResponseResult updateBill(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingQueryService.updateBill(cbillAccountingDTO);
    }

    @PostMapping("/insertBill")
    @ApiOperation(value = "插入单条信息(单纯为练前端功能，不必要集成测试)")
    public ResponseResult insertBill(@RequestBody CbillAccountingVO cbillAccountingVO){
        CbillAccountingDTO cbillAccountingDTO = new CbillAccountingDTO();
        MyBeanUtil.copyProperties(cbillAccountingVO,cbillAccountingDTO);
        return cbillAccountingQueryService.insertBill(cbillAccountingDTO);
    }

    @PostMapping("/batchInsert")
    @ApiOperation(value = "批量插入信息(单纯为练前端功能，不必要集成测试)")
    public ResponseResult batchInsertBill(@RequestBody List<CbillAccountingVO> cbillAccountingVOList){
        List<CbillAccountingDTO> cbillAccountingDTOList = new ArrayList<>();
        MyBeanUtil.copyProperties(cbillAccountingVOList,cbillAccountingDTOList);
        return cbillAccountingQueryService.batchInsert(cbillAccountingDTOList);
    }

}
