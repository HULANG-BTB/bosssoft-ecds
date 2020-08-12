package com.bosssoft.ecds.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
* @Version 1.0
* @author LiDaShan
* @Date 2020/8/10
* @Content: 编码方式
*/
@ApiModel("编码类型枚举类")
public enum StringType implements Serializable {
    /**
     * BASE64编码
     */
    @ApiModelProperty("BASE64编码")
    BASE64,
    /**
     * HEX编码
     */
    @ApiModelProperty("BASE64编码")
    HEX,
    /**
     * ASCII编码
     */
    @ApiModelProperty("BASE64编码")
    ASCII;
}
