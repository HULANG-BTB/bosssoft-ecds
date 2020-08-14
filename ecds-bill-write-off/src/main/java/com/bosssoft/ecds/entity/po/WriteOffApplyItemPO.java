package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hjr
 */
@Data
@TableName("ube_writeoff_apply_item")
@ApiModel(value="WriteoffApplyItemPO对象")
public class WriteOffApplyItemPO {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "对应主表的业务单号")
    @TableField("f_pid")
    private String fPid;

    @ApiModelProperty(value = "票据编码")
    @TableField("f_bill_code")
    private String fBillCode;

    @ApiModelProperty(value = "票据名称")
    @TableField("f_bill_name")
    private String fBillName;

    @ApiModelProperty(value = "票据ID")
    @TableField("f_bill_id")
    private String fBillId;

    @ApiModelProperty(value = "票据代码")
    @TableField("f_batch_code")
    private String fBatchCode;

    @ApiModelProperty(value = "票据种类")
    @TableField("f_type")
    private String fType;

    @ApiModelProperty(value = "开票日期")
    @TableField("f_date")
    private Date fDate;

    @ApiModelProperty(value = "票据状态 1 正常 2作废")
    @TableField("f_state")
    private Boolean fState;

    @ApiModelProperty(value = "票面金额")
    @TableField("f_amt")
    private BigDecimal fAmt;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer fVersion;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date fCreateTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date fUpdateTime;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long fOperatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String fOperator;
}
