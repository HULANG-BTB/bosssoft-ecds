package com.bosssoft.ecds.pay.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UUidDto
 * @Description 标识用户唯一的UUidDto
 * @auther wangpeng
 * @Date 2020/8/20 9:08
 * @Version 1.0
 **/
@Data
@ApiModel(value = "UUidDto",description = "UUid传输Dto")
public class UUidDto {

    @ApiModelProperty(value = "标识用户唯一的UUid")
    private String UUid;
}
