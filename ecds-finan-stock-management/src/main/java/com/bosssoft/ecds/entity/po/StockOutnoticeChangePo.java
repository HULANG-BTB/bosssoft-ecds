package com.bosssoft.ecds.entity.po;

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
 * @author misheep
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_stock_outnotice_change")
@ApiModel(value="StockOutnoticeChangePo对象", description="")
public class StockOutnoticeChangePo extends Model<StockOutnoticeChangePo> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("f_pid")
    private Long pid;

    @ApiModelProperty(value = "审核状态(0新建1保存2提交3通过4退回）")
    @TableField("f_change_state")
    private Integer changeState;

    @ApiModelProperty(value = "变更日期")
    @TableField("f_change_date")
    private Date changeDate;

    @ApiModelProperty(value = "变更情况")
    @TableField("f_change_situ")
    private String changeSitu;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "变更状态(0无用1新增2修改3删除)")
    @TableField("f_altercode")
    private Integer altercode;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "操作者id")
    @TableField(value = "f_operator_id")
    private Long operatorId;

    @ApiModelProperty(value = "操作者name")
    @TableField(value = "f_operator")
    private String operator;


    public static final String F_ID = "f_id";

    public static final String F_PID = "f_pid";

    public static final String F_CHANGE_STATE = "f_change_state";

    public static final String F_CHANGE_DATE = "f_change_date";

    public static final String F_CHANGE_SITU = "f_change_situ";

    public static final String F_VERSION = "f_version";

    public static final String F_ALTERCODE = "f_altercode";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
