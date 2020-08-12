package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName :  BillItemsInsertVO
 * @Description : 单个票据与项目关联
 * @Author : wuliming
 * @Date: 2020-08-10 22:44
 */
@Data
@ApiModel(value = "包含票据编码与项目id", description = "")
public class BillItemsInsertVO {
    @ApiModelProperty(value = "票据种类编码")
    private String billCode;
    @ApiModelProperty(value = "单位编码")
    private String itemId;
}
