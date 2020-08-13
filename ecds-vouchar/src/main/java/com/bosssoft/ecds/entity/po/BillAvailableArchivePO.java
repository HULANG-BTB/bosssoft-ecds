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
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbr_bill_available_archive")
@ApiModel(value = "BillAvailableArchivePO对象", description = "可用票据归档")
public class BillAvailableArchivePO extends Model<BillAvailableArchivePO> {


    public static final String F_ID = "f_id";
    public static final String F_AGEN_CODE = "f_agen_code";
    public static final String F_BILL_CODE = "f_bill_code";
    public static final String F_BILL_NAME = "f_bill_name";
    public static final String F_NUMBER = "f_number";
    public static final String F_CREATE_TIME = "f_create_time";
    public static final String F_UPDATE_TIME = "f_update_time";
    public static final String F_OPERATOR_ID = "f_operator_id";
    public static final String F_OPERATOR = "f_operator";
    public static final String F_VERSION = "f_version";
    public static final String F_LOGIC_DELETE = "f_logic_delete";
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;
    @ApiModelProperty(value = "票据编码")
    @TableField("f_bill_code")
    private String billCode;
    @ApiModelProperty(value = "票据名称 ")
    @TableField("f_bill_name")
    private String billName;
    @ApiModelProperty(value = "票据核定数量")
    @TableField("f_number")
    private Long number;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
