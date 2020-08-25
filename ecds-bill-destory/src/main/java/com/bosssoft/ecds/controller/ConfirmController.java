package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.DestroyDto;
import com.bosssoft.ecds.entity.vo.ApplyVo;
import com.bosssoft.ecds.entity.vo.ConfirmVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.ConfirmService;
import com.bosssoft.ecds.service.feign.BillStockDestroyFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qiuheng
 * @create: 2020-08-21 10:26
 **/
@Slf4j
@RestController
@RequestMapping(value = "confirm")
@Api(description = "票据销毁审核接口")
public class ConfirmController {
    @Autowired
    private ConfirmService confirmService;

    @Autowired
    private BillStockDestroyFeign billStockDestroyFeign;

    @ApiOperation(value = "新增票据销毁审核信息", notes = "新增票据销毁审核信息")
    @ApiImplicitParam(name = "confirmVo", value = "审核VO", paramType = "query", required = true, dataType = "Object")
    @RequestMapping(value = "insertConfirmInfo", method = RequestMethod.POST)
    public ResponseResult insertConfirmInfo(@RequestBody ConfirmVo confirmVo) {
        confirmService.insertConfirmInfo(confirmVo);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "库存票据销毁",notes = "根据单位ID、票据八位数的编码、仓库ID、票据起始号、票据终止号销毁单位库存票据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fAgenIdCode", value = "根据单位ID", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fBillBatchCode", value = "票据八位数的编码", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fWarehouseId", value = "仓库ID", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fBillNo1", value = "票据起始号", paramType = "query", required = true, dataType = "long"),
            @ApiImplicitParam(name = "fBillNo2", value = "票据终止号", paramType = "query", required = true, dataType = "long"),
    })
    @RequestMapping(value = "destroyStockBill", method = RequestMethod.DELETE)
    public ResponseResult destroyStockBill(String fAgenIdCode, String fBillBatchCode, String fWarehouseId, long fBillNo1, long fBillNo2){
        DestroyDto destroyDto = new DestroyDto();
        destroyDto.setfAgenIdCode(fAgenIdCode);
        destroyDto.setfBillBatchCode(fBillBatchCode);
        destroyDto.setfWarehouseId(fWarehouseId);
        destroyDto.setfBillNo1(fBillNo1);
        destroyDto.setfBillNo2(fBillNo2);
        int res = billStockDestroyFeign.destroyStockBill(destroyDto);
        log.info(String.valueOf(res));
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
