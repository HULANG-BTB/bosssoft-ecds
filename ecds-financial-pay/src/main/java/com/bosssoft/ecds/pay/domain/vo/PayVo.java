package com.bosssoft.ecds.pay.domain.vo;

import com.bosssoft.ecds.pay.domain.dto.PayDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import com.bosssoft.ecds.pay.domain.dto.UneCbillItemDto;
import java.math.BigDecimal;

/**
 * @ClassName PayVo
 * @Description 登录成功返回的缴费相关信息Vo
 * @auther wangpeng
 * @Date 2020/8/20 9:14
 * @Version 1.0
 **/

@Data
@ApiModel(value = "PayVo",description = "登录成功返回的缴费相关信息Vo")
public class PayVo {

    @ApiModelProperty(value = "票据校验码")
    private String checkCode;

    @ApiModelProperty(value = "缴款人电话")
    private String fPayerTel;

    @ApiModelProperty(value = "缴费项目明细Dto")
    private PayDto payDto;

    @ApiModelProperty(value = "缴费项目总数")
    private Integer total;

    @ApiModelProperty(value = "缴费总金额")
    private BigDecimal money;

    @ApiModelProperty("判断是否已经缴费（0代表未缴费,1代表已经缴费）")
    private Integer type;

}
