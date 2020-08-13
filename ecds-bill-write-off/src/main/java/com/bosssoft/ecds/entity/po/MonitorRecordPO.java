package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fab_monitor_record")
@ApiModel(value="MonitorRecordPO对象")
public class MonitorRecordPO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    @ApiModelProperty(value = "区划ID")
    @TableField("f_rgn_id")
    private String fRgnId;

    @ApiModelProperty(value = "监控事项ID")
    @TableField("f_evt_id")
    private String fEvtId;

    @ApiModelProperty(value = "监控事项编码")
    @TableField("f_evt_code")
    private String fEvtCode;

    @ApiModelProperty(value = "监控事项名称")
    @TableField("f_evt_name")
    private String fEvtName;

    @ApiModelProperty(value = "监管机构ID")
    @TableField("f_mcm_id")
    private String fMcmId;

    @ApiModelProperty(value = "预警监控机构编码")
    @TableField("f_mcm_code")
    private String fMcmCode;

    @ApiModelProperty(value = "预警监控机构名称")
    @TableField("f_mcm_name")
    private String fMcmName;

    @ApiModelProperty(value = "产生预警记录的业务数据ID")
    @TableField("f_busi_id")
    private String fBusiId;

    @ApiModelProperty(value = "预警发生机构ID")
    @TableField("f_omonitor_id")
    private String fOmonitorId;

    @ApiModelProperty(value = "发生预警机构编码")
    @TableField("f_omonitor_code")
    private String fOmonitorCode;

    @ApiModelProperty(value = "发生预警机构名称")
    @TableField("f_omonitor_name")
    private String fOmonitorName;

    @ApiModelProperty(value = "处理机构ID")
    @TableField("f_hndlorg_id")
    private String fHndlorgId;

    @ApiModelProperty(value = "处理机构编码")
    @TableField("f_hndlorg_code")
    private String fHndlorgCode;

    @ApiModelProperty(value = "处理机构名称")
    @TableField("f_hndlorg_name")
    private String fHndlorgName;

    @ApiModelProperty(value = "预警内容")
    @TableField("f_mnt_cont")
    private String fMntCont;

    @ApiModelProperty(value = "预警发生时间")
    @TableField("f_mnt_time")
    private Date fMntTime;

    @ApiModelProperty(value = "处理状态（0：否，1：是）")
    @TableField("f_is_hndl")
    private Boolean fIsHndl;

    @ApiModelProperty(value = "处理人员ID")
    @TableField("f_hndl_id")
    private String fHndlId;

    @ApiModelProperty(value = "处理人员")
    @TableField("f_hndl_name")
    private String fHndlName;

    @ApiModelProperty(value = "处理时间")
    @TableField("f_hndl_time")
    private Date fHndlTime;

    @ApiModelProperty(value = "预警记录处理说明")
    @TableField("f_hndl_desc")
    private String fHndlDesc;

    @ApiModelProperty(value = "是否关闭（0：否，1：是）")
    @TableField("f_is_close")
    private Boolean fIsClose;

    @ApiModelProperty(value = "关闭人ID")
    @TableField("f_clsman_id")
    private String fClsmanId;

    @ApiModelProperty(value = "关闭人")
    @TableField("f_clsman")
    private String fClsman;

    @ApiModelProperty(value = "关闭时间")
    @TableField("f_cls_time")
    private Date fClsTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date fUpdateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date fCreateTime;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer fVersion;

    @ApiModelProperty(value = "操作人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long fOperatorId;

    @ApiModelProperty(value = "操作人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String fOperator;
}
