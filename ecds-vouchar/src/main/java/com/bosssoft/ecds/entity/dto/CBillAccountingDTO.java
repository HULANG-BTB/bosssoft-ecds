package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuke
 */
@Data
@ApiModel(value = "CBillAccountingDTO对象", description = "")
public class CBillAccountingDTO {
    @ApiModelProperty(value = "单位代码")
    private String agenIdcode;

    @ApiModelProperty("票据已经使用数量")
    private Long billUsedNumber;

    @ApiModelProperty("票据已经审验数量")
    private Long billCheckedNumber;

    @ApiModelProperty("票据未审验数量")
    private Long billUncheckNumber;

}
