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
@ApiModel(value = "ArchiveOverViewDto", description = "归档总览信息dto")
public class ArchiveOverViewDTO implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

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

    @ApiModelProperty(value = "票据领用数量")
    private Long applyNumber;

    @ApiModelProperty(value = "票据使用数量")
    private Long useNumber;

    @ApiModelProperty(value = "票据已审核数量")
    private Long authorNumber;

    @ApiModelProperty(value = "票据未审核数量")
    private Long unAuthorNumber;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
