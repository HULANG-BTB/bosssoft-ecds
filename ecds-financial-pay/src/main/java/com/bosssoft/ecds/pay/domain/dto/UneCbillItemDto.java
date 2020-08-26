package com.bosssoft.ecds.pay.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "UneCbillItemDto",description = "缴费明细Dto")
public class UneCbillItemDto {
    /**
     * 项目编码
     */
    @ApiModelProperty(value = "项目编码")
    private String fItemCode;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String fItemName;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    private String fUnits;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private int fNumber;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private double fAmt;

}
