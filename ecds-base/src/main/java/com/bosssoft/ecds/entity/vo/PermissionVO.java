package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author AloneH
 * @since 2020-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PermissionVO对象", description="")
public class PermissionVO extends Model<PermissionVO> {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "父权限ID")
    private Long parentId;

    @ApiModelProperty(value = "地址", required = true)
    @NotNull(message = "URL不能为空")
    private String url;

    @ApiModelProperty(value = "名称", required = true)
    @NotNull(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty(value = "访问方法", required = true)
    @NotNull(message = "访问方式不能为空")
    private String method;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "子权限列表")
    private List<PermissionVO> children;

}