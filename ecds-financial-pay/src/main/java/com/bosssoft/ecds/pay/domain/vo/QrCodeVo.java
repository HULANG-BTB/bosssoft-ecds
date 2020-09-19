package com.bosssoft.ecds.pay.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName QrCodeVo
 * @Description 二维码Vo
 * @auther wangpeng
 * @Date 2020/8/20 11:17
 * @Version 1.0
 **/
@Data
@ApiModel(value = "QrCodeVo",description = "二维码Vo")
public class QrCodeVo {

    @ApiModelProperty("二维码存储的用户标识")
    String uuid;

    @ApiModelProperty("二维码图片")
    String image;
}
