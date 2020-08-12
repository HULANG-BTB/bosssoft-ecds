package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="BillWarnDto对象", description="票据预警传输对象")
public class BillWarnDto implements Serializable {

    @ApiModelProperty(value = "票据预警时间")
    private Date mntTime;

    @ApiModelProperty(value = "预警内容")
    private String mntCont;

    @ApiModelProperty(value = "预警票号编码")
    private String billCode;

    @ApiModelProperty(value = "预警票号")
    private String billNo;

    @ApiModelProperty(value = "监控事项(规则)")
    private String evtName;

    @ApiModelProperty(value = "操作人")
    private String operator;
}
