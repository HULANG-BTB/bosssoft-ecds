package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dao.BillNumAllDao;
import com.bosssoft.ecds.domain.search.FbbStockDestroySearch;
import com.bosssoft.ecds.domain.vo.MultiBillVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author syf
 * @Date 2020/8/17 17:15
 */
@RestController
@RequestMapping("/report")
@Api("票据库存")
public class FbbStockController {
    @Autowired
    private BillNumAllDao billNumAllDao;


    @ApiOperation("获取最近七年库存数量")
    @GetMapping("/yearnum/{bill}")
    public QueryResponseResult selectYearNum(@PathVariable("bill") String bill) {
        List<Integer> num = billNumAllDao.getPastYears(bill);
        return new QueryResponseResult(CommonCode.SUCCESS, num);
    }

    @ApiOperation("获取某年所有月库存数量")
    @PostMapping("/monthnum")
    public QueryResponseResult selectMonthNum(@RequestBody FbbStockDestroySearch fbbStockDestroySearch) {
        List<Integer> num = billNumAllDao.getPastMonths(fbbStockDestroySearch);
        return new QueryResponseResult(CommonCode.SUCCESS, num);
    }

    @ApiOperation("获取某年所有种类票据库存数量")
    @GetMapping("/multibillnum/{date}")
    public QueryResponseResult getMultiBillNum(@PathVariable("date") String date) {
        List<MultiBillVO> num = billNumAllDao.getMultiBillNum(date);
        return new QueryResponseResult(CommonCode.SUCCESS, num);
    }
}