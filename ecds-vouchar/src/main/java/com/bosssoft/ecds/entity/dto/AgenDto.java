package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "AgenDto", description = "单位信息dto")
public class AgenDto implements Serializable {
    @ApiModelProperty("公司编码")
    private String agenCode;

    @ApiModelProperty("单位名称")
    private String agenName;

    @ApiModelProperty(value = "助记码")
    private String mnem;

    @ApiModelProperty(value = "级次")
    private Integer level;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

    @ApiModelProperty(value = "单位分类")
    private String kindName;

    @ApiModelProperty(value = "单位地址")
    private String linkAddr;

    @ApiModelProperty(value = "单位负责人")
    private String linkMan;

    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
