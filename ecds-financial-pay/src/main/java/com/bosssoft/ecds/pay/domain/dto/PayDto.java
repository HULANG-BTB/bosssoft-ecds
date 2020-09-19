package com.bosssoft.ecds.pay.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PaymentDto
 * @Description 查询单位返回的缴费信息Dto
 * @auther wangpeng
 * @Date 2020/8/20 10:10
 * @Version 1.0
 **/
@Data
@ApiModel(value = "PaymentDto",description = "查询单位返回的缴费信息Dto")
public class PayDto {

    @ApiModelProperty("缴款人")
    private String payerName;

    @ApiModelProperty("缴费项目明细")
    private List<UneCbillItemDto> uneCbillItems;
}
