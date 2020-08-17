package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuke
 */
@Data
@ApiModel(value = "BillCheckVO", description = "票据审验信息展示对象")
public class BillCheckVO implements Serializable {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signTime;

    @ApiModelProperty(value = "审核状态")
    private Boolean signStatus;
}
