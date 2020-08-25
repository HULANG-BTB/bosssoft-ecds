package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
@TableName("faa_permission")
@ApiModel(value="PermissionPO对象", description="")
public class PermissionPO extends Model<PermissionPO> {


    @ApiModelProperty(value = "ID")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父权限ID")
    @TableField("f_parent_id")
    private Long parentId;

    @ApiModelProperty(value = "地址")
    @TableField("f_url")
    private String url;

    @ApiModelProperty(value = "名称")
    @TableField("f_name")
    private String name;

    @ApiModelProperty(value = "访问方法")
    @TableField("f_method")
    private String method;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;

    @ApiModelProperty(value = "乐观锁")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;

    @TableField(exist = false)
    @ApiModelProperty(value = "子权限列表")
    private List<PermissionPO> children;

    public static final String F_ID = "f_id";

    public static final String F_PARENT_ID = "f_parent_id";

    public static final String F_URL = "f_url";

    public static final String F_NAME = "f_name";

    public static final String F_METHOD = "f_method";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    public static final String F_VERSION = "f_version";

    public static final String F_NOTE = "f_note";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}