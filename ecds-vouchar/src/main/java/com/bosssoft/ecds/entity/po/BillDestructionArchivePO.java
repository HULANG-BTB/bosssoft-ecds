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
@TableName("fbr_bill_destruction")
@ApiModel(value="BillDestructionPO对象", description="归档票据销毁")
public class BillDestructionArchivePO extends Model<BillDestructionArchivePO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "票据代码")
    @TableField("f_batch_code")
    private String batchCode;

    @ApiModelProperty(value = "预警票据起始号码")
    @TableField("f_bill_no1")
    private Long billNo1;

    @ApiModelProperty(value = "预警票据终止号码")
    @TableField("f_bill_no2")
    private Long billNo2;

    @ApiModelProperty(value = "票据数量")
    @TableField("f_number")
    private Integer number;

    @ApiModelProperty(value = "票据使用状态")
    @TableField("f_usage_status")
    private String usageStatus;

    @ApiModelProperty(value = "票据销毁业务单号")
    @TableField("f_destory_no")
    private String destoryNo;

    @ApiModelProperty(value = "票据销毁时间")
    @TableField("f_destory_date")
    private Date destoryDate;

    @ApiModelProperty(value = "票据销毁编制人")
    @TableField("f_apply_author")
    private String applyAuthor;

    @ApiModelProperty(value = "审核人")
    @TableField("f_change_man")
    private String changeMan;

    @ApiModelProperty(value = "票据销毁备注信息")
    @TableField("f_destory_memo")
    private String destoryMemo;

    @ApiModelProperty(value = "票据销毁类型")
    @TableField("f_destory_type")
    private String destoryType;

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

    public static final String F_BATCH_CODE = "f_batch_code";

    public static final String F_BILL_NO1 = "f_bill_no1";

    public static final String F_BILL_NO2 = "f_bill_no2";

    public static final String F_NUMBER = "f_number";

    public static final String F_USAGE_STATUS = "f_usage_status";

    public static final String F_DESTORY_NO = "f_destory_no";

    public static final String F_DESTORY_DATE = "f_destory_date";

    public static final String F_APPLY_AUTHOR = "f_apply_author";

    public static final String F_CHANGE_MAN = "f_change_man";

    public static final String F_DESTORY_MEMO = "f_destory_memo";

    public static final String F_DESTORY_TYPE = "f_destory_type";

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
