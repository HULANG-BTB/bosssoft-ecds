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
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_finan_bill")
@ApiModel(value="FinanBillPo对象", description="")
public class FinanBillPo extends Model<FinanBillPo> {


    @ApiModelProperty(value = "财政票据表_主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "票据编码（18位）")
    @TableField("f_bill_code")
    private String billCode;

    @ApiModelProperty(value = "票据代码（8位）")
    @TableField("f_bill_precode")
    private String billPrecode;

    @ApiModelProperty(value = "票据ID（10）")
    @TableField("f_bill_id")
    private String billId;

    @ApiModelProperty(value = "仓库ID")
    @TableField("f_warehouse_id")
    private Long warehouseId;

    @ApiModelProperty(value = "票据名称")
    @TableField("f_bill_name")
    private String billName;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private Long agenCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("f_agen_name")
    private String agenName;

    @ApiModelProperty(value = "生效日期")
    @TableField("f_eff_date")
    private Date effDate;

    @ApiModelProperty(value = "失效日期")
    @TableField("f_exp_date")
    private Date expDate;

    @ApiModelProperty(value = "经办人ID")
    @TableField("f_oper_id")
    private Long operId;

    @ApiModelProperty(value = "经办人")
    @TableField("f_operator")
    private String operator;

    @ApiModelProperty(value = "经办日期")
    @TableField("f_ope_date")
    private Date opeDate;

    @ApiModelProperty(value = "是否发放")
    @TableField("f_is_putout")
    private Integer isPutout;

    @ApiModelProperty(value = "是否退票")
    @TableField("f_is_return")
    private Integer isReturn;

    @ApiModelProperty(value = "是否作废")
    @TableField("f_is_invalid")
    private Integer isInvalid;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "版本号（乐观锁）")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_code")
    private String rgnCode;

    @ApiModelProperty(value = "核销状态（0未核销1需要核销2不需核销）")
    @TableField("f_hx_status")
    private Integer hxStatus;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_BILL_CODE = "f_bill_code";

    public static final String F_BILL_PRECODE = "f_bill_precode";

    public static final String F_BILL_ID = "f_bill_id";

    public static final String F_WAREHOUSE_ID = "f_warehouse_id";

    public static final String F_BILL_NAME = "f_bill_name";

    public static final String F_AGEN_CODE = "f_agen_code";

    public static final String F_AGEN_NAME = "f_agen_name";

    public static final String F_EFF_DATE = "f_eff_date";

    public static final String F_EXP_DATE = "f_exp_date";

    public static final String F_OPER_ID = "f_oper_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_OPE_DATE = "f_ope_date";

    public static final String F_IS_PUTOUT = "f_is_putout";

    public static final String F_IS_RETURN = "f_is_return";

    public static final String F_IS_INVALID = "f_is_invalid";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_VERSION = "f_version";

    public static final String F_RGN_CODE = "f_rgn_code";

    public static final String F_HX_STATUS = "f_hx_status";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
