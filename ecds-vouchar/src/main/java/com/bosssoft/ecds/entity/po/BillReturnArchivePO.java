package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbr_bill_return_archive")
@ApiModel(value = "BillReturnPO对象", description = "归档退票")
public class BillReturnArchivePO extends Model<BillReturnArchivePO> {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "票据批次id")
    @TableField("f_bill_batch_id")
    private Long billBatchId;

    @ApiModelProperty(value = "票据代码")
    @TableField("f_bill_code")
    private String billCode;

    @ApiModelProperty(value = "票据起始号码")
    @TableField("f_bill_no1")
    private String billNo1;

    @ApiModelProperty(value = "票据终止号码")
    @TableField("f_bill_no2")
    private String billNo2;

    @ApiModelProperty(value = "退票数量")
    @TableField("f_number")
    private Long number;

    @ApiModelProperty(value = "票据退票人姓名")
    @TableField("f_returner")
    private String returner;

    @ApiModelProperty(value = "票据退票时间")
    @TableField("f_date")
    private Date date;

    @ApiModelProperty(value = "票据退票原因")
    @TableField("f_return_reason")
    private String returnReason;

    @ApiModelProperty(value = "退票审核时间")
    @TableField("f_change_time")
    private Date changeTime;

    @ApiModelProperty(value = "退票审核人姓名")
    @TableField("f_change_name")
    private String changeName;

    @ApiModelProperty(value = "退票审核状态")
    @TableField("f_change_state")
    private Integer changeState;

    @ApiModelProperty(value = "退票审核意见（未通过原因）")
    @TableField("f_change_situ")
    private String changeSitu;

    @ApiModelProperty(value = "归档状态")
    @TableField("f_is_save")
    private Boolean isSave;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public static final String F_BILL_BATCH_ID = "f_bill_batch_id";

    public static final String F_BILL_CODE = "f_bill_code";

    public static final String F_BILL_NO1 = "f_bill_no1";

    public static final String F_BILL_NO2 = "f_bill_no2";

    public static final String F_NUMBER = "f_number";

    public static final String F_RETURNER = "f_returner";

    public static final String F_DATE = "f_date";

    public static final String F_RETURN_REASON = "f_return_reason";

    public static final String F_CHANGE_TIME = "f_change_time";

    public static final String F_CHANGE_NAME = "f_change_name";

    public static final String F_CHANGE_STATE = "f_change_state";

    public static final String F_CHANGE_SITU = "f_change_situ";

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
