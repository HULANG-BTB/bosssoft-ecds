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
@ApiModel(value = "BillWarnDto对象", description = "票据预警传输对象")
public class BillWarnDTO implements Serializable {

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "票据预警时间")
    private Date mntTime;

    @ApiModelProperty(value = "监控事项(规则)")
    private String evtName;

    @ApiModelProperty(value = "预警内容")
    private String mntCont;

    @ApiModelProperty(value = "处理状态")
    private String isHndl;

    @ApiModelProperty(value = "处理人")
    private String hndlName;

    @ApiModelProperty(value = "处理时间")
    private Date hndlTime;
}
