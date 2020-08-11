package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @author wy
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_subject")
@ApiModel(value="SubjectPO对象", description="")
public class SubjectPO extends Model<SubjectPO> {


    @ApiModelProperty(value = "科目id")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "年度")
    @TableField("f_year")
    private String year;

    @ApiModelProperty(value = "科目编码")
    @TableField("f_sub_code")
    private String subCode;

    @ApiModelProperty(value = "科目名称")
    @TableField("f_sub_name")
    private String subName;

    @ApiModelProperty(value = "层级")
    @TableField("f_level")
    private Integer level;

    @ApiModelProperty(value = "是否底级")
    @TableField("f_is_leaf")
    private Boolean isLeaf;

    @ApiModelProperty(value = "父级ID")
    @TableField("f_parent_id")
    private Long parentId;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_YEAR = "f_year";

    public static final String F_SUB_CODE = "f_sub_code";

    public static final String F_SUB_NAME = "f_sub_name";

    public static final String F_LEVEL = "f_level";

    public static final String F_IS_LEAF = "f_is_leaf";

    public static final String F_PARENT_ID = "f_parent_id";

    public static final String F_IS_ENABLE = "f_is_enable";

    public static final String F_NOTE = "f_note";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_VERSION = "f_version";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
