package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_writeoff_billsummary")
@ApiModel(value = "WriteoffBillsummaryPO对象", description = "")
public class WriteoffBillsummaryPO extends Model<WriteoffBillsummaryPO> {


    public static final String F_ID = "f_id";
    public static final String F_PID = "f_pid";
    public static final String F_SORT_NO = "f_sort_no";
    public static final String F_BILL_CODE = "f_bill_code";
    public static final String F_BILL_NAME = "f_bill_name";
    public static final String F_BILL_ID = "f_bill_id";
    public static final String F_BATCH_CODE = "f_batch_code";
    public static final String F_AMT = "f_amt";
    public static final String F_NUMBER = "f_number";
    public static final String F_INVNUM = "f_invnum";
    public static final String F_CREATE_TIME = "f_create_time";
    public static final String F_UPDATE_TIME = "f_update_time";
    public static final String F_VERSION = "f_version";
    public static final String F_OPERATOR_ID = "f_operator_id";
    public static final String F_OPERATOR = "f_operator";
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "审验单ID")
    @TableField("f_pid")
    private String pid;
    @ApiModelProperty(value = "序号")
    @TableField("f_sort_no")
    private Integer sortNo;
    @ApiModelProperty(value = "票据编码")
    @TableField("f_bill_code")
    private String billCode;
    @ApiModelProperty(value = "票据名称")
    @TableField("f_bill_name")
    private String billName;
    @ApiModelProperty(value = "票据ID")
    @TableField("f_bill_id")
    private String billId;
    @ApiModelProperty(value = "票据代码")
    @TableField("f_batch_code")
    private String batchCode;
    @ApiModelProperty(value = "票面金额")
    @TableField("f_amt")
    private BigDecimal amt;
    @ApiModelProperty(value = "数量")
    @TableField("f_number")
    private Integer number;
    @ApiModelProperty(value = "开票份数")
    @TableField("f_invnum")
    private Integer invnum;
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
    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;
    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
