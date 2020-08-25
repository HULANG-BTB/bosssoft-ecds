package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemVO {
    @ApiModelProperty(value = "项目编码")
    private String itemId;

    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty(value = "标准金额")
    private BigDecimal charge;

    private int number;

    @ApiModelProperty(value = "计量单位")
    private String units;
}
