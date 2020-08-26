package com.bosssoft.ecds.pay.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UUidVo
 * @Description 标识用户唯一的UUidVo
 * @auther wangpeng
 * @Date 2020/8/20 9:08
 * @Version 1.0
 **/
@Data
@ApiModel(value = "UUidVo",description = "UUid传输Vo")
public class UUidVo {

    @ApiModelProperty(value = "标识用户唯一的UUid")
    private String UUid;
}
