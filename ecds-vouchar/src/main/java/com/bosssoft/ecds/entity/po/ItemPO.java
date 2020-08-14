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
 * @author liuke
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_item")
@ApiModel(value = "ItemPO对象", description = "")
public class ItemPO extends Model<ItemPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划id")
    @TableField("f_rgn_id")
    private String rgnId;

    @ApiModelProperty(value = "项目编码")
    @TableField("f_item_id")
    private String itemId;

    @ApiModelProperty(value = "项目名称")
    @TableField("f_item_name")
    private String itemName;

    @ApiModelProperty(value = "项目分类编码")
    @TableField("f_item_sort_id")
    private String itemSortId;

    @ApiModelProperty(value = "助记码")
    @TableField("f_mnen")
    private String mnen;

    @ApiModelProperty(value = "项目生效日期")
    @TableField("f_item_effdate")
    private Date itemEffdate;

    @ApiModelProperty(value = "项目失效日期")
    @TableField("f_item_expdate")
    private Date itemExpdate;

    @ApiModelProperty(value = "记录生效日期")
    @TableField("f_effdate")
    private Date effdate;

    @ApiModelProperty(value = "记录截止日期")
    @TableField("f_expdate")
    private Date expdate;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Integer isenable;

    @ApiModelProperty(value = "收入类别")
    @TableField("f_incom_sort_code")
    private String incomSortCode;

    @ApiModelProperty(value = "资金性质")
    @TableField("f_fundsnature_code")
    private String fundsnatureCode;

    @ApiModelProperty(value = "预算科目")
    @TableField("f_subject")
    private String subject;

    @ApiModelProperty(value = "收缴方式")
    @TableField("f_paymode")
    private String paymode;

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
    private Integer logicDelete;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;


    public static final String F_ID = "f_id";

    public static final String F_RGN_ID = "f_rgn_id";

    public static final String F_ITEM_ID = "f_item_id";

    public static final String F_ITEM_NAME = "f_item_name";

    public static final String F_ITEM_SORT_ID = "f_item_sort_id";

    public static final String F_MNEN = "f_mnen";

    public static final String F_ITEM_EFFDATE = "f_item_effdate";

    public static final String F_ITEM_EXPDATE = "f_item_expdate";

    public static final String F_EFFDATE = "f_effdate";

    public static final String F_EXPDATE = "f_expdate";

    public static final String F_ISENABLE = "f_isenable";

    public static final String F_INCOM_SORT_CODE = "f_incom_sort_code";

    public static final String F_FUNDSNATURE_CODE = "f_fundsnature_code";

    public static final String F_SUBJECT = "f_subject";

    public static final String F_PAYMODE = "f_paymode";

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
