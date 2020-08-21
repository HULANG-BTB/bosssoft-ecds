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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liuke
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fne_cbill_accounting")
@ApiModel(value = "CbillAccountingPO对象", description = "")
public class CbillAccountingPO extends Model<CbillAccountingPO> {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "单位代码")
    @TableField("f_agen_idcode")
    private String agenIdcode;
    @ApiModelProperty(value = "单位名称")
    @TableField("f_agen_name")
    private String agenName;
    @ApiModelProperty(value = "开票点id")
    @TableField("f_place_id")
    private String placeId;
    @ApiModelProperty(value = "开票时间")
    @TableField("f_agen_time")
    private Date agenTime;
    @ApiModelProperty(value = "入账单位类型(0.开票单位  1.缴款单位)")
    @TableField("f_type")
    private Integer type;
    @ApiModelProperty(value = "票据批次号")
    @TableField("f_bill_batch_id")
    private String billBatchId;
    @ApiModelProperty(value = "票据校验码")
    @TableField("f_bill_serial_id")
    private String billSerialId;
    @ApiModelProperty(value = "票据号码")
    @TableField("f_bill_no")
    private String billNo;
    @ApiModelProperty(value = "入账时间")
    @TableField("f_time")
    private Date time;
    @ApiModelProperty(value = "入账金额")
    @TableField("f_account")
    private BigDecimal account;
    @ApiModelProperty(value = "入账凭证号")
    @TableField("f_account_id")
    private Long accountId;
    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @ApiModelProperty(value = "删除状态(1.已经删除 0.未删除)")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;
    @ApiModelProperty(value = "应缴金额")
    @TableField("f_wait_account")
    private BigDecimal waitAccount;
    @ApiModelProperty(value = "入账方式(1.现金 2.电子 3.等等)")
    @TableField("f_account_type")
    private Integer accountType;
    @ApiModelProperty(value = "入账完成状态(0.未完成 1.已完成)")
    @TableField("f_account_status")
    private Boolean accountStatus;
    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;
    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;
    @ApiModelProperty(value = "缴款人电话")
    @TableField("f_payer_tel")
    private String payerTel;
    @ApiModelProperty(value = "备注")
    @TableField("f_node")
    private String node;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
