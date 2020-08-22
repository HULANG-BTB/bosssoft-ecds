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
@ApiModel(value = "BillApplyDTO", description = "票据领用信息")
public class BillApplyDTO implements Serializable {

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    @ApiModelProperty(value = "票据代码")
    private String billCode;

    @ApiModelProperty("票据名称")
    private String billName;

    @ApiModelProperty(value = "申请批次数量")
    private Integer batchNum;

    @ApiModelProperty("联系人")
    private String linkMan;

    @ApiModelProperty("联系人电话")
    private String linkTel;
}
