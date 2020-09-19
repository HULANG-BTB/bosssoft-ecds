package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴志鸿
 * @date 2020/8/16
 * @description
 */
@Data
public class PlaceInfoDTO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "开票点编码")
    private String placeId;

    @ApiModelProperty(value = "开票点名称")
    private String placeName;
}
