package com.bosssoft.ecds.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("归档总览信息查询对象")
public class OverViewArchiveQuery {
    @ApiModelProperty("单位编码")
    private String agenIdCode;
    @ApiModelProperty("单位名称")
    private String agenName;
}
