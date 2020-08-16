package com.bosssoft.ecds.security.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author AloneH
 * @since 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PermissionPO对象", description="")
public class PermissionVO extends Model<PermissionVO> {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "访问方法")
    private String method;

}