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
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fnr_bill_pay_archive")
@ApiModel(value = "BillPayVoucherPO对象", description = "票据缴销")
public class BillPayArchivePO extends Model<BillPayArchivePO> {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("f_agen_name")
    private String agenName;

    @ApiModelProperty(value = "票据使用数量")
    @TableField("f_use_number")
    private int useNumber;

    @ApiModelProperty(value = "应缴金额")
    @TableField("f_wait_account")
    private BigDecimal waitAccount;

    @ApiModelProperty(value = "实缴金额")
    @TableField("f_account")
    private BigDecimal account;

    @ApiModelProperty(value = "汇总时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @TableField(value = "f_summary_time", fill = FieldFill.INSERT)
    private Date summaryTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
