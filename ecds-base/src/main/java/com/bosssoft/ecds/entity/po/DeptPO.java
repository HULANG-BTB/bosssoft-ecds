package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author vihenne
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_dept")
@ApiModel(value="FabDeptPO对象", description="")
public class DeptPO extends Model<DeptPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_id")
    private String rgnId;

    @ApiModelProperty(value = "部门编码")
    @TableField("f_dept_code")
    private String deptCode;

    @ApiModelProperty(value = "部门名称")
    @TableField("f_dept_name")
    private String deptName;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Boolean isenable;

    @ApiModelProperty(value = "联系人")
    @TableField("f_link_man")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    @TableField("f_link_tel")
    private String linkTel;

    @ApiModelProperty(value = "联系电话")
    @TableField("f_addr")
    private String addr;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;

    @ApiModelProperty(value = "维护类型")
    @TableField("f_alter_code")
    private String alterCode;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;


    public static final String F_ID = "f_id";

    public static final String F_RGN_ID = "f_rgn_id";

    public static final String F_DEPT_CODE = "f_dept_code";

    public static final String F_DEPT_NAME = "f_dept_name";

    public static final String F_ISENABLE = "f_isenable";

    public static final String F_LINK_MAN = "f_link_man";

    public static final String F_LINK_TEL = "f_link_tel";

    public static final String F_ADDR = "f_addr";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    public static final String F_ALTER_CODE = "f_alter_code";

    public static final String F_VERSION = "f_version";

    public static final String F_NOTE = "f_note";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
