package com.boss.demo.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @author WE
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_writeoff_billitem")
@ApiModel(value="WriteoffBillitemPO对象", description="")
public class WriteoffBillitemPO extends Model<WriteoffBillitemPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "审验单ID")
    @TableField("f_pid")
    private String pid;

    @ApiModelProperty(value = "审验顺序 （序号）")
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

    @ApiModelProperty(value = "数量")
    @TableField("f_number")
    private Integer number;

    @ApiModelProperty(value = "票据起始号")
    @TableField("f_bill_no1")
    private String billNo1;

    @ApiModelProperty(value = "票据结束号")
    @TableField("f_bill_no2")
    private String billNo2;

    @ApiModelProperty(value = "票面金额")
    @TableField("f_amt")
    private BigDecimal amt;

    @ApiModelProperty(value = "开票份数")
    @TableField("f_invnum")
    private Integer invnum;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
