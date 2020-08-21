package com.bosssoft.ecds.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName AccBaseInfoDTO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="AccBaseInfoDto对象", description="发票基本信息")
public class AccBaseInfoDTO {

    @ApiModelProperty(value = "单位代码")
    private String agenIdcode;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "票据校验码")
    private String checkCode;

    @ApiModelProperty(value = "开票地点id")
    private String placeId;

    @ApiModelProperty(value = "待缴款金额")
    private BigDecimal totalAmt;

    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;

}
