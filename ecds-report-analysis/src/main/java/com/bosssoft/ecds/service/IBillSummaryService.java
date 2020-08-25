package com.bosssoft.ecds.service;

import com.bosssoft.ecds.domain.dto.TodayBillKindDto;
import com.bosssoft.ecds.domain.dto.TodayBillSummaryDto;
import com.bosssoft.ecds.domain.vo.WeekBillSummaryVO;

import java.util.List;

/**
 * 开票汇总业务类
 */
public interface IBillSummaryService {

    /**
     * 查询今日开票汇总
     * @return
     */
    List<TodayBillSummaryDto> selectTodayBillSummary();

    /**
     * 查询今日不同单位的开票数量
     * @return
     */
    List<TodayBillKindDto> selectTodayBillKind();

    /**
     * 查询本周每一天的开票数量，包含每一天各单位的开票数量
     * @return
     */
    List<WeekBillSummaryVO> selectWeekBillSummary();
}