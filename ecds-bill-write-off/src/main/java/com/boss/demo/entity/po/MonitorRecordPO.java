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
@TableName("fab_monitor_record")
@ApiModel(value="MonitorRecordPO对象", description="")
public class MonitorRecordPO extends Model<MonitorRecordPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划ID")
    @TableField("f_rgn_id")
    private String rgnId;

    @ApiModelProperty(value = "监控事项ID")
    @TableField("f_evt_id")
    private String evtId;

    @ApiModelProperty(value = "监控事项编码")
    @TableField("f_evt_code")
    private String evtCode;

    @ApiModelProperty(value = "监控事项名称")
    @TableField("f_evt_name")
    private String evtName;

    @ApiModelProperty(value = "监管机构ID")
    @TableField("f_mcm_id")
    private String mcmId;

    @ApiModelProperty(value = "预警监控机构编码")
    @TableField("f_mcm_code")
    private String mcmCode;

    @ApiModelProperty(value = "预警监控机构名称")
    @TableField("f_mcm_name")
    private String mcmName;

    @ApiModelProperty(value = "产生预警记录的业务数据ID")
    @TableField("f_busi_id")
    private String busiId;

    @ApiModelProperty(value = "预警发生机构ID")
    @TableField("f_omonitor_id")
    private String omonitorId;

    @ApiModelProperty(value = "发生预警机构编码")
    @TableField("f_omonitor_code")
    private String omonitorCode;

    @ApiModelProperty(value = "发生预警机构名称")
    @TableField("f_omonitor_name")
    private String omonitorName;

    @ApiModelProperty(value = "处理机构ID")
    @TableField("f_hndlorg_id")
    private String hndlorgId;

    @ApiModelProperty(value = "处理机构编码")
    @TableField("f_hndlorg_code")
    private String hndlorgCode;

    @ApiModelProperty(value = "处理机构名称")
    @TableField("f_hndlorg_name")
    private String hndlorgName;

    @ApiModelProperty(value = "预警内容")
    @TableField("f_mnt_cont")
    private String mntCont;

    @ApiModelProperty(value = "预警发生时间")
    @TableField("f_mnt_time")
    private Date mntTime;

    @ApiModelProperty(value = "处理状态（0：否，1：是）")
    @TableField("f_is_hndl")
    private Boolean isHndl;

    @ApiModelProperty(value = "处理人员ID")
    @TableField("f_hndl_id")
    private String hndlId;

    @ApiModelProperty(value = "处理人员")
    @TableField("f_hndl_name")
    private String hndlName;

    @ApiModelProperty(value = "处理时间")
    @TableField("f_hndl_time")
    private Date hndlTime;

    @ApiModelProperty(value = "预警记录处理说明")
    @TableField("f_hndl_desc")
    private String hndlDesc;

    @ApiModelProperty(value = "是否关闭（0：否，1：是）")
    @TableField("f_is_close")
    private Boolean isClose;

    @ApiModelProperty(value = "关闭人ID")
    @TableField("f_clsman_id")
    private String clsmanId;

    @ApiModelProperty(value = "关闭人")
    @TableField("f_clsman")
    private String clsman;

    @ApiModelProperty(value = "关闭时间")
    @TableField("f_cls_time")
    private Date clsTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "操作人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;


    public static final String F_ID = "f_id";

    public static final String F_RGN_ID = "f_rgn_id";

    public static final String F_EVT_ID = "f_evt_id";

    public static final String F_EVT_CODE = "f_evt_code";

    public static final String F_EVT_NAME = "f_evt_name";

    public static final String F_MCM_ID = "f_mcm_id";

    public static final String F_MCM_CODE = "f_mcm_code";

    public static final String F_MCM_NAME = "f_mcm_name";

    public static final String F_BUSI_ID = "f_busi_id";

    public static final String F_OMONITOR_ID = "f_omonitor_id";

    public static final String F_OMONITOR_CODE = "f_omonitor_code";

    public static final String F_OMONITOR_NAME = "f_omonitor_name";

    public static final String F_HNDLORG_ID = "f_hndlorg_id";

    public static final String F_HNDLORG_CODE = "f_hndlorg_code";

    public static final String F_HNDLORG_NAME = "f_hndlorg_name";

    public static final String F_MNT_CONT = "f_mnt_cont";

    public static final String F_MNT_TIME = "f_mnt_time";

    public static final String F_IS_HNDL = "f_is_hndl";

    public static final String F_HNDL_ID = "f_hndl_id";

    public static final String F_HNDL_NAME = "f_hndl_name";

    public static final String F_HNDL_TIME = "f_hndl_time";

    public static final String F_HNDL_DESC = "f_hndl_desc";

    public static final String F_IS_CLOSE = "f_is_close";

    public static final String F_CLSMAN_ID = "f_clsman_id";

    public static final String F_CLSMAN = "f_clsman";

    public static final String F_CLS_TIME = "f_cls_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_VERSION = "f_version";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_OPERATOR = "f_operator";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
