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
 *  归档领用信息
 * </p>
 *
 * @author liuke
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ubr_crt_info")
@ApiModel(value="CrtInfoPO对象", description="")
public class CrtInfoPO extends Model<CrtInfoPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_idcode")
    private String agenIdcode;

    @ApiModelProperty(value = "申请人姓名")
    @TableField("f_apply_user")
    private String applyUser;

    @ApiModelProperty(value = "申请时间")
    @TableField("f_apply_time")
    private Date applyTime;

    @ApiModelProperty(value = "票据批次号")
    @TableField("f_batch")
    private String batch;

    @ApiModelProperty(value = "票据起始票号")
    @TableField("f_bill_no1")
    private String billNo1;

    @ApiModelProperty(value = "票据结束票号")
    @TableField("f_bill_no2")
    private String billNo2;

    @ApiModelProperty(value = "领用仓库id")
    @TableField("f_warehouse_id")
    private Long warehouseId;

    @ApiModelProperty(value = "票据领用数量")
    @TableField("f_apply_number")
    private Long applyNumber;

    @ApiModelProperty(value = "票据使用数量")
    @TableField("f_use_number")
    private Long useNumber;

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

    public static final String F_APPLY_USER = "f_apply_user";

    public static final String F_APPLY_TIME = "f_apply_time";

    public static final String F_BATCH = "f_batch";

    public static final String F_BILL_NO1 = "f_bill_no1";

    public static final String F_BILL_NO2 = "f_bill_no2";

    public static final String F_WAREHOUSE_ID = "f_warehouse_id";

    public static final String F_APPLY_NUMBER = "f_apply_number";

    public static final String F_USE_NUMBER = "f_use_number";

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
