package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "BillApplyDto", description = "单位端票据领用信息")
public class BillApplyDto {

    @ApiModelProperty(value = "申请人姓名")
    private String applyUser;

    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    @ApiModelProperty(value = "票据批次id")
    private String batchId;

    @ApiModelProperty(value = "票据代码")
    private String billCode;

    @ApiModelProperty(value = "票据名称")
    private String billName;

    @ApiModelProperty(value = "票据起始票号")
    private String billNo1;

    @ApiModelProperty(value = "票据结束票号")
    private String billNo2;

    /*@ApiModelProperty(value = "领用仓库id")
    private Long warehouseId;*/

    @ApiModelProperty(value = "票据领用数量")
    private Long number;

    @ApiModelProperty(value = "票据使用数量")
    private Long useNumber;
}
