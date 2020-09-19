package com.bosssoft.ecds.pay.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PayLoginDto
 * @Description 缴费系统登录数据Dto
 * @auther wangpeng
 * @Date 2020/8/20 9:08
 * @Version 1.0
 **/
@Data
@ApiModel(value = "PayLoginDto",description = "缴费系统登录Dto")
public class PayLoginDto {

    @ApiModelProperty(value = "票据校验码")
    private String checkCode;

    @ApiModelProperty(value = "缴款人电话")
    private String fPayerTel;

}

