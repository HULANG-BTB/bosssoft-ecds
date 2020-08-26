package com.bosssoft.ecds.domain.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一周开票信息类
 * @author LiDaShan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("一周开票信息类")
public class WeekBillSummaryVO {

    /**
     * 开票单位名称
     */
    @ApiModelProperty("开票单位名称")
    private String agenname;
    /**
     * 一周开票数量
     */
    @ApiModelProperty("一周开票数量")
    private int[] billnum;
}
