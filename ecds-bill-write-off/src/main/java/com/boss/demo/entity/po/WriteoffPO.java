package com.boss.demo.entity.po;

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
@TableName("fbe_writeoff")
@ApiModel(value="WriteoffPO对象", description="")
public class WriteoffPO extends Model<WriteoffPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_code")
    private String rgnCode;

    @ApiModelProperty(value = "业务单号")
    @TableField("f_no")
    private String no;

    @ApiModelProperty(value = "单位识别码")
    @TableField("f_agen_id_code")
    private String agenIdCode;

    @ApiModelProperty(value = "审验日期")
    @TableField("f_check_date")
    private Date checkDate;

    @ApiModelProperty(value = "编制人")
    @TableField("f_author")
    private String author;

    @ApiModelProperty(value = "编制日期")
    @TableField("f_date")
    private Date date;

    @ApiModelProperty(value = "审验结果：1 良好 2 合格 3 问题 4 整改通过")
    @TableField("f_check_result")
    private Boolean checkResult;

    @ApiModelProperty(value = "备注")
    @TableField("f_memo")
    private String memo;

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

    @ApiModelProperty(value = "状态：1 未审验，2 已审验")
    @TableField("f_change_state")
    private Boolean changeState;

    @ApiModelProperty(value = "审验申请单业务唯一号")
    @TableField("f_apply_id")
    private String applyId;

    @ApiModelProperty(value = "接收数据批次号")
    @TableField("f_batch_no")
    private Integer batchNo;

    @ApiModelProperty(value = "操作人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;


    public static final String F_ID = "f_id";

    public static final String F_RGN_CODE = "f_rgn_code";

    public static final String F_NO = "f_no";

    public static final String F_AGEN_ID_CODE = "f_agen_id_code";

    public static final String F_CHECK_DATE = "f_check_date";

    public static final String F_AUTHOR = "f_author";

    public static final String F_DATE = "f_date";

    public static final String F_CHECK_RESULT = "f_check_result";

    public static final String F_MEMO = "f_memo";

    public static final String F_VERSION = "f_version";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_CHANGE_STATE = "f_change_state";

    public static final String F_APPLY_ID = "f_apply_id";

    public static final String F_BATCH_NO = "f_batch_no";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
