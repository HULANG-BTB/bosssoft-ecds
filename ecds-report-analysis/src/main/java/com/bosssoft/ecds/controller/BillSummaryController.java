package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.domain.dto.TodayBillKindDto;
import com.bosssoft.ecds.domain.dto.TodayBillSummaryDto;
import com.bosssoft.ecds.domain.vo.TodayBillSummaryVO;
import com.bosssoft.ecds.domain.vo.WeekBillSummaryVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.service.IBillSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 开票汇总控制器
 * @author LiDaShan
 * @date 2020/8/23 21:57
 */
@RestController
@Slf4j
@Api("财政报表模块，开票明细页面")
public class BillSummaryController {

    @Autowired
    private IBillSummaryService billSummaryService;

    /**
     * 查询今日一小时内开票变动
     * @return 统一响应对象
     */
    @GetMapping("/report/todayBillSummary")
    @ApiOperation("查询今日一小时内开票变动")
    public QueryResponseResult selectTodayBillSummary(){
        List<TodayBillSummaryDto> todayBillSummaryDtos = billSummaryService.selectTodayBillSummary();
        // 封装 dto 到 vo
        TodayBillSummaryVO vo = new TodayBillSummaryVO();
        int[] billnum = new int[todayBillSummaryDtos.size()];
        BigDecimal[] billMoneys = new  BigDecimal[todayBillSummaryDtos.size()];
        for (int i = 0; i< todayBillSummaryDtos.size(); i++){
            billnum[i] = todayBillSummaryDtos.get(i).getBillNum();
            billMoneys[i] = todayBillSummaryDtos.get(i).getBillMoney();
        }
        vo.setBillnum(billnum);
        vo.setBillMoneys(billMoneys);
        return new QueryResponseResult(CommonCode.SUCCESS,vo);
    }

    /**
     * 查询今日开票各单位的票据占比
     * @return 统一响应对象
     */
    @GetMapping("/report/todayBillKind")
    @ApiOperation("查询今日开票各单位的票据占比")
    public QueryResponseResult selectTodayBillKind(){
        List<TodayBillKindDto> todayBillKindDtos = billSummaryService.selectTodayBillKind();
        return new QueryResponseResult(CommonCode.SUCCESS, todayBillKindDtos);
    }

    /**
     * 查询近七天每日各单位票据开具数量
     * @return 统一响应对象
     */
    @GetMapping("/report/weekBillSummary")
    @ApiOperation("查询近七天每日票据开具数量")
    public QueryResponseResult selectWeekBillSummary(){
        List<WeekBillSummaryVO> vos = billSummaryService.selectWeekBillSummary();
        return new QueryResponseResult(CommonCode.SUCCESS,vos);
    }
}
