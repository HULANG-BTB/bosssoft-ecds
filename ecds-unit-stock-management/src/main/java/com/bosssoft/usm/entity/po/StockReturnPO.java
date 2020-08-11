package com.bosssoft.usm.entity.po;

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
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhuWen
 * @since 2020-08-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ube_stock_return")
@ApiModel(value="StockReturnPO对象", description="")
public class StockReturnPO extends Model<StockReturnPO> {


    @ApiModelProperty(value = "退票主表_主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务单号")
    @TableField("f_no")
    private Long no;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_code")
    private String rgnCode;

    @ApiModelProperty(value = "单位识别码")
    @TableField("f_agen_id_code")
    private String agenIdCode;

    @ApiModelProperty(value = "编制日期")
    @TableField("f_date")
    private Date date;

    @ApiModelProperty(value = "退票原因")
    @TableField("f_return_reason")
    private String returnReason;

    @ApiModelProperty(value = "退票人")
    @TableField("f_returner")
    private String returner;

    @ApiModelProperty(value = "编制人")
    @TableField("f_author")
    private String author;

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

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "审核状态")
    @TableField("f_change_state")
    private Integer changeState;

    @ApiModelProperty(value = "审核日期")
    @TableField("f_change_date")
    private Date changeDate;

    @ApiModelProperty(value = "经办人")
    @TableField("f_change_man")
    private String changeMan;

    @ApiModelProperty(value = "审核意见")
    @TableField("f_change_situ")
    private String changeSitu;

    @ApiModelProperty(value = "判定是否提交，0未提交，1已经提交")
    @TableField("f_submit_status")
    private Integer submitStatus;


    public static final String F_ID = "f_id";

    public static final String F_NO = "f_no";

    public static final String F_RGN_CODE = "f_rgn_code";

    public static final String F_AGEN_ID_CODE = "f_agen_id_code";

    public static final String F_DATE = "f_date";

    public static final String F_RETURN_REASON = "f_return_reason";

    public static final String F_RETURNER = "f_returner";

    public static final String F_AUTHOR = "f_author";

    public static final String F_MEMO = "f_memo";

    public static final String F_VERSION = "f_version";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_CHANGE_STATE = "f_change_state";

    public static final String F_CHANGE_DATE = "f_change_date";

    public static final String F_CHANGE_MAN = "f_change_man";

    public static final String F_CHANGE_SITU = "f_change_situ";

    public static final String F_SUBMIT_STATUS = "f_submit_status";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
