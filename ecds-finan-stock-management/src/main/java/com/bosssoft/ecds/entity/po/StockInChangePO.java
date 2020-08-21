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
 * @author cheng
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fbe_stock_in_change")
@ApiModel(value = "StockInChangePO对象", description = "")
public class StockInChangePO extends Model<StockInChangePO> {
    
    
    public static final String F_ID = "f_id";
    public static final String F_BUSS_ID = "f_buss_id";
    public static final String F_CHANGE_STATE = "f_change_state";
    public static final String F_CHANGE_DATE = "f_change_date";
    public static final String F_CHANGE_MAN = "f_change_man";
    public static final String F_CHANGE_SITU = "f_change_situ";
    public static final String F_VERSION = "f_version";
    public static final String F_ALTERCODE = "f_altercode";
    public static final String F_CREATE_TIME = "f_create_time";
    public static final String F_UPDATE_TIME = "f_update_time";
    @ApiModelProperty(value = "入库变动表_主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "业务ID")
    @TableField("f_buss_id")
    private Long bussId;
    @ApiModelProperty(value = "变更状态")
    @TableField("f_change_state")
    private Integer changeState;
    @ApiModelProperty(value = "变更日期")
    @TableField("f_change_date")
    private Date changeDate;
    @ApiModelProperty(value = "变更人")
    @TableField("f_change_man")
    private String changeMan;
    @ApiModelProperty(value = "变更情况")
    @TableField("f_change_situ")
    private String changeSitu;
    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;
    @ApiModelProperty(value = "1新增　2修改   3 删除")
    @TableField("f_altercode")
    private Integer altercode;
    @ApiModelProperty(value = "创建日期")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    
}
