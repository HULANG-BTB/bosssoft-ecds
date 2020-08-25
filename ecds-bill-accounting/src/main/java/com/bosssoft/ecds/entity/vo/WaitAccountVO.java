package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName WaitAccountVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/14 17:21
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="WaitAccountVO对象", description="待缴金额")
public class WaitAccountVO {

    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "待缴金额")
    private BigDecimal waitAccount;

}
