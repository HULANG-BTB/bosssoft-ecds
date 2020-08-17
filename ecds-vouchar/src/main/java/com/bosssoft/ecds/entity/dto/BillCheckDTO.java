package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuke
 */
@Data
@ApiModel(value = "BillCheckDto", description = "票据发放审验")
public class BillCheckDTO implements Serializable {
    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "审验单id")
    private String applyId;

    @ApiModelProperty(value = "票据编码")
    private String billCode;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "票据数量")
    private int billNumber;

    @ApiModelProperty(value = "审核人名字")
    private String signName;

    @ApiModelProperty(value = "审核时间")
    private Date signTime;

    @ApiModelProperty(value = "审核状态")
    private Boolean signStatus;

}
