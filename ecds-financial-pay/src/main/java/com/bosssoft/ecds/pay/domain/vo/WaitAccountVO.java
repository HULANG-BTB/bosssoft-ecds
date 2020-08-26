package com.bosssoft.ecds.pay.domain.vo;

/**
 * @ClassName WaitAccountVO
 * @Description
 * @auther wangpeng
 * @Date 2020/8/24 10:09
 * @Version 1.0
 **/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value="WaitAccountDTO传输对象", description="待缴金额")
public class WaitAccountVO {

    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "待缴金额")
    private BigDecimal waitAccount;

    @ApiModelProperty(value = "入账完成状态(0.未完成 1.已完成)")
    private Boolean accountStatus;

    @ApiModelProperty(value = "是否存在该缴款数据(0.不存在 1.存在)")
    private Boolean accountExist;

    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;

}
