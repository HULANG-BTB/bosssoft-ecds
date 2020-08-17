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
@ApiModel(value = "BillApplyVO", description = "票据可用信息展示对象")
public class BillApplyVO implements Serializable {

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
