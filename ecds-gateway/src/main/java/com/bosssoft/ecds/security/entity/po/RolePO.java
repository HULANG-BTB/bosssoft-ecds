package com.bosssoft.ecds.security.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@TableName("faa_role")
@ApiModel(value="RolePO对象", description="")
public class RolePO extends Model<RolePO> {


    @ApiModelProperty(value = "ID")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色Key")
    @TableField("f_role")
    private String role;

    @ApiModelProperty(value = "名称")
    @TableField("f_name")
    private String name;

    @ApiModelProperty(value = "角色描述")
    @TableField("f_description")
    private String description;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

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

    public static final String F_ID = "f_id";

    public static final String F_ROLE = "f_role";

    public static final String F_NAME = "f_name";

    public static final String F_DESCRIPTION = "f_description";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_OPERATOR_ID = "f_operator_id";

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
