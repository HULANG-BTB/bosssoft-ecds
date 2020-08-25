package com.bosssoft.ecds.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 一周开票汇总
 * @author LiDashan
 */
public class WeekBillSummaryDto {

    /**
     * 单位id
     */
    private String agenid;
    /**
     * 票据数量
     */
    private int billnum;
}
