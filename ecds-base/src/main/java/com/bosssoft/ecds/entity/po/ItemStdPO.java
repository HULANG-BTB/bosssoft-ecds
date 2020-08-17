package com.bosssoft.ecds.entity.po;

import java.math.BigDecimal;
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
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_item_std")
@ApiModel(value="ItemStdPO对象", description="")
public class ItemStdPO extends Model<ItemStdPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标准编码")
    @TableField("f_itemstd_code")
    private String itemstdCode;

    @ApiModelProperty(value = "标准名称")
    @TableField("f_itemstd_name")
    private String itemstdName;

    @ApiModelProperty(value = "助记码")
    @TableField("f_mnem")
    private String mnem;

    @ApiModelProperty(value = "项目编码")
    @TableField("f_item_code")
    private String itemCode;

    @ApiModelProperty(value = "标准上限")
    @TableField("f_max_charge")
    private BigDecimal maxCharge;

    @ApiModelProperty(value = "标准金额")
    @TableField("f_charge")
    private BigDecimal charge;

    @ApiModelProperty(value = "标准下限")
    @TableField("f_min_charge")
    private BigDecimal minCharge;

    @ApiModelProperty(value = "计量单位")
    @TableField("f_units")
    private String units;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Integer isenable;

    @ApiModelProperty(value = "标准生效日期")
    @TableField("f_itemstd_effdate")
    private Date itemstdEffdate;

    @ApiModelProperty(value = "标准失效日期")
    @TableField("f_itemstd_expdate")
    private Date itemstdExpdate;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
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

    public static final String F_ITEMSTD_CODE = "f_itemstd_code";

    public static final String F_ITEMSTD_NAME = "f_itemstd_name";

    public static final String F_MNEM = "f_mnem";

    public static final String F_ITEM_CODE = "f_item_code";

    public static final String F_MAX_CHARGE = "f_max_charge";

    public static final String F_MIN_CHARGE = "f_min_charge";

    public static final String F_CHARGE = "f_charge";

    public static final String F_UNITS = "f_units";

    public static final String F_ISENABLE = "f_isenable";

    public static final String F_ITEMSTD_EFFDATE = "f_itemstd_effdate";

    public static final String F_ITEMSTD_EXPDATE = "f_itemstd_expdate";

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
