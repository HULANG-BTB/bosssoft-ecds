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
@TableName("fbe_stock_in")
@ApiModel(value = "StockInPO对象", description = "")
public class StockInPO extends Model<StockInPO> {
    
    
    public static final String F_ID = "f_id";
    public static final String F_NO = "f_no";
    public static final String F_WAREHOUSE_ID = "f_warehouse_id";
    public static final String F_AUTHOR = "f_author";
    public static final String F_DATE = "f_date";
    public static final String F_MEMO = "f_memo";
    public static final String F_VERSION = "f_version";
    public static final String F_CREATE_TIME = "f_create_time";
    public static final String F_UPDATE_TIME = "f_update_time";
    public static final String F_CHANGE_STATE = "f_change_state";
    public static final String F_CHANGE_DATE = "f_change_date";
    public static final String F_CHANGE_MAN = "f_change_man";
    public static final String F_CHANGE_SITU = "f_change_situ";
    public static final String F_LOGIC_DELETE = "f_logic_delete";
    public static final String F_STATUS = "f_status";
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty(value = "业务号")
    @TableField("f_no")
    private Long no;
    @ApiModelProperty(value = "仓库ID")
    @TableField("f_warehouse_id")
    private Long warehouseId;
    @ApiModelProperty(value = "编制人")
    @TableField(value = "f_author", fill = FieldFill.INSERT_UPDATE)
    private String author;
    @ApiModelProperty(value = "编制日期")
    @TableField("f_date")
    private Date date;
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
    @ApiModelProperty(value = "审核时间")
    @TableField("f_change_date")
    private Date changeDate;
    @ApiModelProperty(value = "审核人")
    @TableField("f_change_man")
    private String changeMan;
    @ApiModelProperty(value = "审核意见")
    @TableField("f_change_situ")
    private String changeSitu;
    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Integer logicDelete;
    @ApiModelProperty(value = "入库与否")
    @TableField("f_status")
    private Integer status;
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    
}
