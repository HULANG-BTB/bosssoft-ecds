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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbr_item_voucher")
@ApiModel(value="ItemVoucherPO对象", description="归档可用")
public class ItemVoucherPO extends Model<ItemVoucherPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_idcode")
    private String agenIdCode;

    @ApiModelProperty(value = "科目名称")
    @TableField("f_sub_name")
    private String subName;

    @ApiModelProperty(value = "项目名称")
    @TableField("f_item_name")
    private String itemName;

    @ApiModelProperty(value = "收费费用")
    @TableField("f_charge")
    private BigDecimal charge;

    @ApiModelProperty(value = "归档状态")
    @TableField("f_is_save")
    private Boolean isSave;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
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

    @ApiModelProperty(value = "删除状态")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_AGEN_IDCODE = "f_agen_idcode";

    public static final String F_SUB_NAME = "f_sub_name";

    public static final String F_ITEM_NAME = "f_item_name";

    public static final String F_CHARGE = "f_charge";

    public static final String F_IS_SAVE = "f_is_save";

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