package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzh
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("uab_group")
@ApiModel(value="GroupPO对象", description="")
public class GroupPO extends Model<GroupPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "分组编码")
    @TableField("f_group_code")
    private String groupCode;

    @ApiModelProperty(value = "分组名称")
    @TableField("f_group_name")
    private String groupName;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;


    public static final String F_ID = "f_id";

    public static final String F_AGEN_CODE = "f_agen_code";

    public static final String F_GROUP_CODE = "f_group_code";

    public static final String F_GROUP_NAME = "f_group_name";

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
