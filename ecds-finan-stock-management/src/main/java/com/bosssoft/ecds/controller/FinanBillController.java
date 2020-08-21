package com.bosssoft.ecds.controller;


import cn.hutool.json.JSONUtil;
import com.bosssoft.ecds.entity.dto.ReceiveFinanceapplyDto;
import com.bosssoft.ecds.entity.dto.SentBillDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.FinanBillService;
import com.bosssoft.ecds.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 *      * 接收参数RequestBody处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
 *      * 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@RestController
@RequestMapping("/finan-bill")
@CrossOrigin
@Api(tags = "财政票据管理Controller")
public class FinanBillController {

    @Autowired
    private FinanBillService billService;

    @ApiOperation("获得出库明细列表")
    @PostMapping("/outBills")
    public QueryResponseResult outBills(@RequestBody List<ReceiveFinanceapplyDto> receiveDtos) {
        log.info("Controller:进入outBills方法...");
        List<SentBillDto> sentBillDtos = billService.outBills(receiveDtos);
        log.info("退出方法，data:{}", sentBillDtos);
        return new QueryResponseResult<>(CommonCode.SUCCESS, sentBillDtos);
    }

//        return ResponseUtil.getResponse(
//                ResponseUtil.ResultType.OK.getCode(),
//                ResponseUtil.ResultType.OK.getMsg(),
//                billDto);




}

