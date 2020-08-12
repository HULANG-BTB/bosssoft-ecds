package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "BillCheckDto", description = "票据发放审验")
public class BillCheckDto implements Serializable {
    @ApiModelProperty(value = "票号")
    private String billNo;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "(审核)签名人名字")
    private String signName;

    @ApiModelProperty(value = "审核时间")
    private Date signTime;

    @ApiModelProperty(value = "(审核)签名状态")
    private Boolean signStatus;

    @ApiModelProperty(value = "审核未通过原因")
    private String staReason;
}
