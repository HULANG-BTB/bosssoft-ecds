package com.bosssoft.ecds.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * 今日开票汇总信息类
 * @author LiDaShan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayBillSummaryDto {

    /**
     * 票据数量
     */
    private int billNum;

    /**
     * 开票时间段
     */
    private String summaryTime;

    /**
     * 票据收费总金额
     */
    private BigDecimal billMoney;

}
