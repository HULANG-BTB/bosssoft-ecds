package com.bosssoft.ecds.entity.dto.agendto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴志鸿
 * @date 2020/8/17
 * @description
 */
@Data
public class AlarmDTO {
    @ApiModelProperty(value = "是否预警单位")
    private Boolean isalarmAgen;
}
