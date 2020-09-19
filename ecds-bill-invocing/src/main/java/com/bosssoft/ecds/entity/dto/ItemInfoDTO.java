package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 吴志鸿
 * @date 2020/8/17
 * @description
 */
@Data
public class ItemInfoDTO {
    @ApiModelProperty(value = "项目编码")
    private String itemId;

    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty(value = "标准金额")
    private BigDecimal charge;

    @ApiModelProperty(value = "计量单位")
    private String units;
}
