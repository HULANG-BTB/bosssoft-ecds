package com.bosssoft.ecds.domain.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * 今日开票信息类
 * @author LiDaShan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("今日开票信息类")
public class TodayBillSummaryVO {

    /**
     * 一小时内每5分钟开票数量
     */
    @ApiModelProperty("一小时内每5分钟内开票数量")
    private int[] billnum;

    /**
     * 每5分钟开票收入金额
     */
    @ApiModelProperty("一小时内每5分钟内开票收入")
    private BigDecimal[] billMoneys;
}
