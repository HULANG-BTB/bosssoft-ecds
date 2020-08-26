package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.domain.vo.FbeStockInVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.FbeStockInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 提供入库的查询接口
 * @author zouyou
 * @date 2020/8/22 12:38
 */
@RestController
@RequestMapping("/report/stockIn")
@Api("提供入库信息查询")
public class FbeStockInController {

    @Autowired
    private FbeStockInService fbeStockInService;

    /**
     * 查询入库各票据种类的票据数量，返回一个list集合
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:26
     */
    @GetMapping("/getNum")
    @ApiOperation("获取入库的种类与其对应数量，绘制饼状图")
    public QueryResponseResult<List<FbeStockInVO>> getNum(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockInService.selectNum());
    }

    /**
     * 查询入库的时间与数量对应的信息，返回一个map集合
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:29
     */
    @GetMapping("/getBar")
    @ApiOperation("根据时间对入库的信息进行分类，绘制柱状图")
    public QueryResponseResult<Map<String, int[]>> getBar(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockInService.selectBar());
    }

    /**
     * 查询入库的所有信息，返回一个list集合
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:31
     */
    @GetMapping("/getList")
    @ApiOperation("查询入库的所有信息，返回一个list集合")
    public QueryResponseResult<List<FbeStockInVO>> getList(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockInService.getList());
    }

    /**
     * 查询近七天的入库信息，返回一个list
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult<java.util.List < java.lang.Integer>>
     * @date 2020/8/24 22:41
     */
    @GetMapping("/getNumIndex")
    @ApiOperation("查询近七天的入库信息，返回一个list")
    public QueryResponseResult<List<Integer>> getNumIndex(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockInService.selectNumIndex());
    }
}
