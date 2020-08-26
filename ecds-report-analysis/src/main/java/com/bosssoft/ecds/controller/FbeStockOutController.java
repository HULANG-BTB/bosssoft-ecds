package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.domain.vo.FbeStockOutVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.FbeStockOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 提供出库的查询接口
 * @author zouyou
 * @version 1.0
 * @date 2020/8/17 16:56
 */
@RestController
@RequestMapping("/report/stockOut")
@Api("提供出库信息查询")
public class FbeStockOutController {

    @Autowired
    private FbeStockOutService fbeStockOutService;

    /**
     * 查询出库各票据种类的票据数量，返回一个list集合
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:44
     */
    @GetMapping("/getNum")
    @ApiOperation("获取出库的种类与其对应数量，绘制饼状图")
    public QueryResponseResult<List<FbeStockOutVO>> getNum(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockOutService.selectNum());
    }

    /**
     * 查询出库的时间与数量对应的信息，返回一个map集合
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:44
     */
    @GetMapping("/getBar")
    @ApiOperation("根据时间对出库的信息进行分类，绘制柱状图")
    public QueryResponseResult<Map<String, int[]>> getBar(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockOutService.selectBar());
    }

    /**
     * 查询入库的所有信息，返回一个list集合
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:46
     */
    @GetMapping("/getList")
    @ApiOperation("查询入库的所有信息，返回一个list集合")
    public QueryResponseResult<List<FbeStockOutVO>> getList(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockOutService.getList());
    }

    /**
     * 查询近七天的入库信息，返回一个list
     * @author zouyou
     * @return com.bosssoft.ecds.response.QueryResponseResult
     * @date 2020/8/24 22:46
     */
    @GetMapping("/getNumIndex")
    @ApiOperation("查询近七天的入库信息，返回一个list")
    public QueryResponseResult<List<Integer>> getNumIndex(){
        return new QueryResponseResult<>(CommonCode.SUCCESS,fbeStockOutService.selectNumIndex());
    }
}
