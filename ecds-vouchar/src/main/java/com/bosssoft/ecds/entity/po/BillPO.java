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
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_bill")
@ApiModel(value="BillPO对象", description="票据对象")
public class BillPO extends Model<BillPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "票据编码")
    @TableField("f_code")
    private String code;

    @ApiModelProperty(value = "票据名称")
    @TableField("f_name")
    private String name;

    @ApiModelProperty(value = "票据用途")
    @TableField("f_bill_nature")
    private String billNature;

    @ApiModelProperty(value = "助记码")
    @TableField("f_memory_code")
    private String memoryCode;

    @ApiModelProperty(value = "生效日期")
    @TableField("f_eff_date")
    private Date effDate;

    @ApiModelProperty(value = "失效日期")
    @TableField("f_exp_date")
    private Date expDate;

    @ApiModelProperty(value = "是否分类")
    @TableField("f_check_sort")
    private Integer checkSort;

    @ApiModelProperty(value = "是否定额")
    @TableField("f_check_quota")
    private Integer checkQuota;

    @ApiModelProperty(value = "定额面值")
    @TableField("f_quota_amount")
    private BigDecimal quotaAmount;

    @ApiModelProperty(value = "法定保存年限")
    @TableField("f_safe_year")
    private Integer safeYear;

    @ApiModelProperty(value = "备注")
    @TableField("f_remark")
    private String remark;

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

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "级次")
    @TableField("f_level")
    private Integer level;

    @ApiModelProperty(value = "父级id")
    @TableField("f_pid")
    private Long pid;

    @ApiModelProperty(value = "是否底级 1代表是0代表不是")
    @TableField("f_check_leaf")
    private Integer checkLeaf;

    @ApiModelProperty(value = "票据性质0非税1其他")
    @TableField("f_nature_code")
    private Integer natureCode;

    @ApiModelProperty(value = "票据种类代码")
    @TableField("f_type_code")
    private String typeCode;

    @ApiModelProperty(value = "父級編碼")
    @TableField("f_parent_code")
    private String parentCode;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Integer logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_CODE = "f_code";

    public static final String F_NAME = "f_name";

    public static final String F_BILL_NATURE = "f_bill_nature";

    public static final String F_MEMORY_CODE = "f_memory_code";

    public static final String F_EFF_DATE = "f_eff_date";

    public static final String F_EXP_DATE = "f_exp_date";

    public static final String F_CHECK_SORT = "f_check_sort";

    public static final String F_CHECK_QUOTA = "f_check_quota";

    public static final String F_QUOTA_AMOUNT = "f_quota_amount";

    public static final String F_SAFE_YEAR = "f_safe_year";

    public static final String F_REMARK = "f_remark";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_VERSION = "f_version";

    public static final String F_LEVEL = "f_level";

    public static final String F_PID = "f_pid";

    public static final String F_CHECK_LEAF = "f_check_leaf";

    public static final String F_NATURE_CODE = "f_nature_code";

    public static final String F_TYPE_CODE = "f_type_code";

    public static final String F_PARENT_CODE = "f_parent_code";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
