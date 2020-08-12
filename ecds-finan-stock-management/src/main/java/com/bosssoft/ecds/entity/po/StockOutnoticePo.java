package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @author misheep
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_stock_outnotice")
@ApiModel(value="StockOutnoticePO对象", description="")
public class StockOutnoticePo extends Model<StockOutnoticePo> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务单号")
    @TableField("f_no")
    private Long no;

    @ApiModelProperty(value = "区划编码")
    @TableField("f_rgn_code")
    private String rgnCode;

    @ApiModelProperty(value = "仓库ID")
    @TableField("f_warehouse_id")
    private Long warehouseId;

    @ApiModelProperty(value = "申请日期")
    @TableField("f_date")
    private Date date;

    @ApiModelProperty(value = "领用人")
    @TableField("f_use_man")
    private String useMan;

    @ApiModelProperty(value = "联系电话")
    @TableField("f_link_tel")
    private String linkTel;

    @ApiModelProperty(value = "联系地址")
    @TableField("f_link_address")
    private String linkAddress;

    @ApiModelProperty(value = "编制人")
    @TableField("f_author")
    private String author;

    @ApiModelProperty(value = "备注")
    @TableField("f_memo")
    private String memo;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Boolean version;

    @ApiModelProperty(value = "摘要")
    @TableField("f_abstact")
    private String abstact;

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

    @ApiModelProperty(value = "审核人")
    @TableField("f_change_man")
    private String changeMan;

    @ApiModelProperty(value = "审核意见")
    @TableField("f_change_situ")
    private String changeSitu;

    @ApiModelProperty(value = "逻辑删除(0未删除1删除)")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;


    public static final String F_ID = "f_id";

    public static final String F_NO = "f_no";

    public static final String F_RGN_CODE = "f_rgn_code";

    public static final String F_WAREHOUSE_ID = "f_warehouse_id";

    public static final String F_DATE = "f_date";

    public static final String F_USE_MAN = "f_use_man";

    public static final String F_LINK_TEL = "f_link_tel";

    public static final String F_LINK_ADDRESS = "f_link_address";

    public static final String F_AUTHOR = "f_author";

    public static final String F_MEMO = "f_memo";

    public static final String F_VERSION = "f_version";

    public static final String F_ABSTACT = "f_abstact";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_CHANGE_STATE = "f_change_state";

    public static final String F_CHANGE_DATE = "f_change_date";

    public static final String F_CHANGE_MAN = "f_change_man";

    public static final String F_CHANGE_SITU = "f_change_situ";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
