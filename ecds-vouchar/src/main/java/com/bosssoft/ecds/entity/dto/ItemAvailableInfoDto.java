package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value="ItemAvailableInfoDto", description="可用项目信息传输")
public class ItemAvailableInfoDto implements Serializable {

    @ApiModelProperty(value = "科目名称")
    private String subName;
    /**
     * 项目名称查询fab_bill 表
     */
    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty(value = "收费费用")
    private BigDecimal charge;

    @ApiModelProperty(value = "归档状态")
    private Boolean isSave;

}
