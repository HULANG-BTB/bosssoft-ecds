package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("归档总览简略信息dto")
public class OverViewArchiveDto implements Serializable {
    @ApiModelProperty("显示id或者说显示行号")
    private Long id;
    @ApiModelProperty("公司编码")
    private String agenIdCode;
    @ApiModelProperty("单位名称")
    private String agenName;
    @ApiModelProperty("领用数量")
    private Long applyNumber;
    @ApiModelProperty("使用数量")
    private Long useNumber;
    @ApiModelProperty("已审核的数量")
    private Long authorNumber;
    @ApiModelProperty("未审核数量")
    private Long unAuthorNumber;
}
