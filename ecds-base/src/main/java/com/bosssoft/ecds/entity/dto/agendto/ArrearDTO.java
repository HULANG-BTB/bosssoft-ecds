package com.bosssoft.ecds.entity.dto.agendto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴志鸿
 * @date 2020/8/16
 * @description
 */
@Data
public class ArrearDTO {
    @ApiModelProperty(value = "是否欠缴")
    private Boolean isunpaid;
}
