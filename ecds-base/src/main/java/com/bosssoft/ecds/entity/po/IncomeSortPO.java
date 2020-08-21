package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
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
 * @author AloneH
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_income_sort")
@ApiModel(value = "IncomeSortPO对象", description = "")
public class IncomeSortPO extends Model<IncomeSortPO> {

    /**
     * 收入种类id
     */
    @TableId(value = "f_id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 收入种类编码
     */
    @TableField("f_code")
    private String code;
    /**
     * 收入种类名称
     */
    @TableField("f_name")
    private String name;
    /**
     * 是否底级
     */
    @TableField("f_is_leaf")
    private Boolean leaf;
    /**
     * 级次
     */
    @TableField("f_level")
    private Integer level;
    /**
     * 父级id
     */
    @TableField("f_parent_id")
    private Long parentId;
    /**
     * 备注
     */
    @TableField("f_remark")
    private String remark;
    /**
     * 更新时间
     */
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 版本号
     */
    @TableField("f_version")
    @Version
    private Integer version;
    /**
     * 操作人id
     */
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long operatorId;
    /**
     * 操作人
     */
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;
    /**
     * 删除状态
     */
    @TableField("f_logic_delete")
    private Boolean logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_CODE = "f_code";

    public static final String F_NAME = "f_name";

    public static final String F_IS_LEAF = "f_is_leaf";

    public static final String F_LEVEL = "f_level";

    public static final String F_PARENT_ID = "f_parent_id";

    public static final String F_REMARK = "f_remark";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_VERSION = "f_version";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return id;
    }

}
