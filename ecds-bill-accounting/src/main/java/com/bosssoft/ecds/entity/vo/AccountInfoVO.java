package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName AccountInfoVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/17 15:03
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="StatusVO对象", description="入账状态")
public class AccountInfoVO {
    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "待缴金额")
    private BigDecimal waitAccount;

    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;
}
