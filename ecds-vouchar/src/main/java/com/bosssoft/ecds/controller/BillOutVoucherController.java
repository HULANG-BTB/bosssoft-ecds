package com.bosssoft.ecds.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api("票据发票")
@RestController
@RequestMapping("/bill-out-voucher-po")
public class BillOutVoucherController {
    @ApiOperation(value = "swagger2")
    @ApiResponses(value = { @ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败")})
    @PostMapping("/hello")
    public String testSwagger(){

        return "hello swagger";
    }
}

