package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.domain.search.LifeSearch;
import com.bosssoft.ecds.domain.vo.FbeLifeVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.FbeLifeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 生命周期的查询信息，提供了根据票据码查询票据信息的接口
 * @author zouyou
 * @version 1.0
 * @description 生命周期模块的controller层
 * @date 2020/8/24 18:38
 */
@RestController
@RequestMapping("/report/life")
@Api(value = "提供生命周期信息的查询")
public class FbeLifeController {

    @Autowired
    private FbeLifeService fbeLifeService;

    /**
     * 从前端获取参数存入lifeSearch对象，根据billcode返回查询到的票据信息
     * @param lifeSearch
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:17
     */
    @PostMapping("/getLifeList")
    @ApiOperation("根据票据号查询此票据的生命周期信息")
    public QueryResponseResult<List<FbeLifeVO>> getLifeList(@RequestBody LifeSearch lifeSearch) {
        return new QueryResponseResult<>(CommonCode.SUCCESS, fbeLifeService.getLifeList(lifeSearch));
    }
}
