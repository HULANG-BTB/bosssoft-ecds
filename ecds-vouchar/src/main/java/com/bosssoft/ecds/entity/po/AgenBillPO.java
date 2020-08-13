package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("fab_agen_bill")
@ApiModel(value = "AgenBillPO对象", description = "")
public class AgenBillPO extends Model<AgenBillPO> {


    public static final String F_ID = "f_id";
    public static final String F_AGEN_IDCODE = "f_agen_idcode";
    public static final String F_TYPE_CODE = "f_type_code";
    public static final String F_OPERATOR = "f_operator";
    public static final String F_OPERATOR_ID = "f_operator_id";
    public static final String F_CREATE_TIME = "f_create_time";
    public static final String F_UPDATE_TIME = "f_update_time";
    public static final String F_NOTE = "f_note";
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_idcode")
    private String agenIdcode;
    @ApiModelProperty(value = "票据编码")
    @TableField("f_type_code")
    private String typeCode;
    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;
    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;
    @ApiModelProperty(value = "创建日期")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
