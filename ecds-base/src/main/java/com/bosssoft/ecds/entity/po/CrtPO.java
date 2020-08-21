package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author vihenne
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("uab_crt")
@ApiModel(value="UabCrtPO对象", description="")
public class CrtPO extends Model<CrtPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("f_agen_name")
    private String agenName;

    @ApiModelProperty(value = "准购证号")
    @TableField("f_crt_code")
    private String crtCode;

    @ApiModelProperty(value = "准购证名称")
    @TableField("f_crt_name")
    private String crtName;

    @ApiModelProperty(value = "办证日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("f_issuedate")
    private Date issuedate;

    @ApiModelProperty(value = "单位法人证号")
    @TableField("f_legalno")
    private String legalno;

    @ApiModelProperty(value = "收费许可证号")
    @TableField("f_chargno")
    private String chargno;

    @ApiModelProperty(value = "罚没许可证号")
    @TableField("f_fineno")
    private String fineno;

    @ApiModelProperty(value = "收费委托书号")
    @TableField("f_proxyno")
    private String proxyno;

    @ApiModelProperty(value = "联系地址")
    @TableField("f_address")
    private String address;

    @ApiModelProperty(value = "单位负责人")
    @TableField("f_linkman")
    private String linkman;

    @ApiModelProperty(value = "单位负责人电话")
    @TableField("f_linkman_tel")
    private String linkmanTel;

    @ApiModelProperty(value = "财务负责人")
    @TableField("f_finmgr")
    private String finmgr;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Boolean logicDelete;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Boolean isenable;


    public static final String F_ID = "f_id";

    public static final String F_AGEN_CODE = "f_agen_code";

    public static final String F_AGEN_NAME = "f_agen_name";

    public static final String F_CRT_CODE = "f_crt_code";

    public static final String F_CRT_NAME = "f_crt_name";

    public static final String F_ISSUEDATE = "f_issuedate";

    public static final String F_LEGALNO = "f_legalno";

    public static final String F_CHARGNO = "f_chargno";

    public static final String F_FINENO = "f_fineno";

    public static final String F_PROXYNO = "f_proxyno";

    public static final String F_ADDRESS = "f_address";

    public static final String F_LINKMAN = "f_linkman";

    public static final String F_LINKMAN_TEL = "f_linkman_tel";

    public static final String F_FINMGR = "f_finmgr";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    public static final String F_VERSION = "f_version";

    public static final String F_NOTE = "f_note";

    public static final String F_ISENABLE = "f_isenable";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
