package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hujierong
 * @date 2020-8-19
 */
@Data
@TableName("ube_writeoff_apply_income")
@ApiModel(value="WriteOffIncomePO对象")
public class WriteOffApplyIncomePO {
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "对应主表的业务单号")
    @TableField("f_pid")
    private String fPid;

    @ApiModelProperty(value = "项目编码")
    @TableField("f_item_code")
    private String fItemCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("f_item_name")
    private String fItemName;

    @ApiModelProperty(value = "计量单位")
    @TableField("f_units")
    private String fUnits;

    @ApiModelProperty(value = "金额")
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
