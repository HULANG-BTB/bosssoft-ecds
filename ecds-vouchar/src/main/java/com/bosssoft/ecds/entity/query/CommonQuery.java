package com.bosssoft.ecds.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuke
 */
@Data
@ApiModel(value = "CommonQuery", description = "通用的查询对象")
public class CommonQuery {
    @ApiModelProperty("单位编码")
    private String agenCode;

    @ApiModelProperty("所查页码")
    private Integer page;

    @ApiModelProperty("每页数量")
    private Integer limit;
}
