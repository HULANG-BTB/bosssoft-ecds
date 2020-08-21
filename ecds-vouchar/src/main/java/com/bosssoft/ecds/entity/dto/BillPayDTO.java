package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuke
 */
@Data
@ApiModel(value = "BillPayDTO", description = "票据缴费信息传输对象")
public class BillPayDTO implements Serializable {

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "票据使用数量")
    private int useNumber;

    @ApiModelProperty(value = "应缴金额")
    private BigDecimal waitAccount;

    @ApiModelProperty(value = "实缴金额")
    private BigDecimal account;

    @ApiModelProperty(value = "汇总时间")
    private Date summaryTime;
}
