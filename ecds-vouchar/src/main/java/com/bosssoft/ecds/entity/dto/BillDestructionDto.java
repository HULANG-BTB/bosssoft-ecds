package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "BillDestructionDto", description = "票据销毁dto")
public class BillDestructionDto implements Serializable {
    @ApiModelProperty(value = "票据代码")
    private String batchCode;

    @ApiModelProperty(value = "票据名")
    private String billName;

    @ApiModelProperty(value = "预警票据起始号码")
    private Long billNo1;

    @ApiModelProperty(value = "预警票据终止号码")
    private Long billNo2;

    @ApiModelProperty(value = "票据数量")
    private Integer number;

    @ApiModelProperty(value = "票据使用状态")
    private String usageStatus;

    @ApiModelProperty(value = "票据销毁业务单号")
    private String destoryNo;

    @ApiModelProperty(value = "票据销毁时间")
    private Date destoryDate;

    @ApiModelProperty(value = "票据销毁编制人")
    private String applyAuthor;

    @ApiModelProperty(value = "审核人")
    private String changeMan;

    @ApiModelProperty(value = "票据销毁备注信息")
    private String destoryMemo;

    @ApiModelProperty(value = "票据销毁类型")
    private String destoryType;
}
