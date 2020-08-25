package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuke
 */
@Data
@ApiModel(value = "BillAvailableVO", description = "可用票据展示对象")
public class BillAvailableVO implements Serializable {

    @ApiModelProperty("公司编码")
    private String agenCode;

    @ApiModelProperty(value = "票据代码")
    private String billCode;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "票据领用数量")
    private Long number;
}
