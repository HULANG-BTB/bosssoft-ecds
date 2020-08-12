package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@ApiModel(value="RolePO对象", description="")
public class RoleDTO extends Model<RoleDTO> {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "角色Key")
    private String role;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean logicDelete;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "权限列表")
    private List<PermissionDTO> permissions;

}