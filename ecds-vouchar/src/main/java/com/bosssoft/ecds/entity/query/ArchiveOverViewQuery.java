package com.bosssoft.ecds.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author liuke
 */
@Data
@ApiModel(value = "OverViewArchiveQuery", description = "归档总览信息查询对象")
public class ArchiveOverViewQuery {
    @ApiModelProperty("单位编码")
    private String agenCode;

    @ApiModelProperty("单位名称")
    private String agenName;

    @ApiModelProperty("所查页码")
    private Integer page;

    @ApiModelProperty("每页数量")
    private Integer limit;
}
