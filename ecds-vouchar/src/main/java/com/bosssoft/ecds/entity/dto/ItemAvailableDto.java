package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ItemAvailableDto", description = "可用项目传输对象")
public class ItemAvailableDto {
    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "项目名称")
    private String itemCode;

    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty(value = "项目助记码")
    private String mnen;
}
