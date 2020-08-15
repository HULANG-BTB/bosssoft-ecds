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
@TableName("fbr_bill_warn_archive")
@ApiModel(value = "MonitorRecordVoucherPO对象", description = "归档预警")
public class BillWarnArchivePO extends Model<BillWarnArchivePO> {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "票据预警时间")
    @TableField("f_mnt_time")
    private Date mntTime;

    @ApiModelProperty(value = "预警内容")
    @TableField("f_mnt_cont")
    private String mntCont;

    @ApiModelProperty(value = "监控事项(规则)")
    @TableField("f_evt_name")
    private String evtName;

    @ApiModelProperty(value = "处理状态")
    @TableField("f_is_hndl")
    private String isHndl;

    @ApiModelProperty(value = "处理人")
    @TableField("f_hndl_name")
    private String hndlName;

    @ApiModelProperty(value = "处理时间")
    @TableField("f_hndl_time")
    private Date hndlTime;

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
