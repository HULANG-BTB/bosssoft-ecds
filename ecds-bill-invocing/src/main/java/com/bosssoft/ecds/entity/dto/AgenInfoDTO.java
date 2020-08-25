package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 吴志鸿
 * @date 2020/8/16
 * @description
 */
@Data
public class AgenInfoDTO {
    @ApiModelProperty(value = "区划ID")
    private String rgnId;

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "单位开票点信息")
    List<PlaceInfoDTO> places;
}
