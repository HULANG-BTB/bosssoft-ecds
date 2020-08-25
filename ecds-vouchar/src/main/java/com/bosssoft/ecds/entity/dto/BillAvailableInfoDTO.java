package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuyeqing
 */
@Data
@ApiModel(value = "BillAvailableInfoDto", description = "可用票据信息传输类")
public class BillAvailableInfoDTO implements Serializable {

    @ApiModelProperty("公司编码")
    private String agenCode;

    @ApiModelProperty(value = "票据代码")
    private String billCode;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "票据领用数量")
    private Long number;
}
