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

@Data
@TableName("fbe_writeoff_billitem")
@ApiModel(value="WriteOffBillitemPO对象")
public class WriteOffBillItemPO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "审验单ID")
    @TableField("f_pid")
    private String fPid;

    @ApiModelProperty(value = "审验顺序 （序号）")
    @TableField("f_sort_no")
    private Integer fSortNo;

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

    @ApiModelProperty(value = "数量")
    @TableField("f_number")
    private Integer fNumber;

    @ApiModelProperty(value = "票据起始号")
    @TableField("f_bill_no1")
    private String fBillNo1;

    @ApiModelProperty(value = "票据结束号")
    @TableField("f_bill_no2")
    private String fBillNo2;

    @ApiModelProperty(value = "票面金额")
    @TableField("f_amt")
    private BigDecimal fAmt;

    @ApiModelProperty(value = "开票份数")
    @TableField("f_invnum")
    private Integer fInvNum;

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
