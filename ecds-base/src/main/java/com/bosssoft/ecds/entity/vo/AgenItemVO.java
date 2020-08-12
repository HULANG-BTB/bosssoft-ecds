package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/12
 * @description
 */
@Data
@ApiModel(value="AgenItemVO对象", description="")
public class AgenItemVO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "单位编码")
    private String agenIdcode;

    @ApiModelProperty(value = "项目编码")
    private String itemCode;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;
}
