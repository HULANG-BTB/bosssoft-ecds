package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dao.FneCbillAccountingDao;
import com.bosssoft.ecds.domain.search.FneCbillAccountingSearch;
import com.bosssoft.ecds.domain.vo.FneCbillAccountingVO;
import com.bosssoft.ecds.domain.vo.FneCbillAccountingWeekVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.FneCbillAccountingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName FneCbillAccountingController
 * @Description FneCbillAccountingController
 * @auther wangpeng
 * @Date 2020/8/19 17:04
 * @Version 1.0
 **/

@RestController
@Slf4j
public class FneCbillAccountingController {

    @Autowired
    FneCbillAccountingService fneCbillAccountingService;

    @Autowired
    FneCbillAccountingDao fneCbillAccountingDao;

    @PostMapping("/report/getAllByFTime")
    @ApiOperation(value = "以入账时间为条件进行查询")
    public  QueryResponseResult getAllByFTime(@RequestBody FneCbillAccountingSearch fneCbillAccountingSearch){
        List<FneCbillAccountingVO> fneCbillAccountingVOList = fneCbillAccountingService.searchTime(fneCbillAccountingSearch);
        return new QueryResponseResult(CommonCode.SUCCESS,fneCbillAccountingVOList);
    }

    @PostMapping("/report/getAll")
    @ApiOperation(value = "多条件进行查询")
    public QueryResponseResult getAll(@RequestBody FneCbillAccountingSearch fneCbillAccountingSearch){
        List<FneCbillAccountingVO> fneCbillAccountingVOList = fneCbillAccountingService.searchAll(fneCbillAccountingSearch);
        return new QueryResponseResult(CommonCode.SUCCESS,fneCbillAccountingVOList);
    }

    @PostMapping("/report/getWeek")
    @ApiOperation(value = "近七天入账金额展示")
    public QueryResponseResult getWeek(){
        FneCbillAccountingWeekVO fneCbillAccountingWeekVO = new FneCbillAccountingWeekVO();
        fneCbillAccountingWeekVO.setFAccountSumList(fneCbillAccountingDao.searchWeek());
        if(fneCbillAccountingWeekVO.getFAccountSumList().size()> 0){
            return new QueryResponseResult(CommonCode.SUCCESS,fneCbillAccountingWeekVO.getFAccountSumList());
        }else{
            return new QueryResponseResult(CommonCode.FAIL,"查询失败");
        }
    }
}
