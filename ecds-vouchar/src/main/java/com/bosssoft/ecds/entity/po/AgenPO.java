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
 * @author liuke
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fab_agen")
@ApiModel(value="AgenPO对象", description="公司对象")
public class AgenPO extends Model<AgenPO> {


    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划ID")
    @TableField("f_rgn_id")
    private String rgnId;

    @ApiModelProperty(value = "单位编码")
    @TableField("f_agen_code")
    private String agenCode;

    @ApiModelProperty(value = "生效日期")
    @TableField("f_eff_date")
    private Date effDate;

    @ApiModelProperty(value = "失效日期")
    @TableField("f_exp_date")
    private Date expDate;

    @ApiModelProperty(value = "单位名称")
    @TableField("f_agen_name")
    private String agenName;

    @ApiModelProperty(value = "助记码")
    @TableField("f_mnem")
    private String mnem;

    @ApiModelProperty(value = "级次")
    @TableField("f_level")
    private Integer level;

    @ApiModelProperty(value = "父级识别码")
    @TableField("f_pid_code")
    private String pidCode;

    @ApiModelProperty(value = "是否底级")
    @TableField("f_isleaf")
    private Boolean isleaf;

    @ApiModelProperty(value = "组织机构代码")
    @TableField("f_org_code")
    private String orgCode;

    @ApiModelProperty(value = "主管部门编码")
    @TableField("f_dept_code")
    private String deptCode;

    @ApiModelProperty(value = "归口财政部门ID")
    @TableField("f_findept_id")
    private Long findeptId;

    @ApiModelProperty(value = "所属行业代码")
    @TableField("f_ind_code")
    private String indCode;

    @ApiModelProperty(value = "单位分类代码")
    @TableField("f_sort_code")
    private String sortCode;

    @ApiModelProperty(value = "单位类型代码")
    @TableField("f_type_code")
    private String typeCode;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Boolean isenable;

    @ApiModelProperty(value = "是否开票单位")
    @TableField("f_istick_agen")
    private Boolean istickAgen;

    @ApiModelProperty(value = "是否欠缴")
    @TableField("f_isunpaid")
    private Boolean isunpaid;

    @ApiModelProperty(value = "是否预警单位")
    @TableField("f_isalarm_agen")
    private Boolean isalarmAgen;

    @ApiModelProperty(value = "地址")
    @TableField("f_addr")
    private String addr;

    @ApiModelProperty(value = "邮政编码")
    @TableField("f_zip")
    private String zip;

    @ApiModelProperty(value = "单位负责人")
    @TableField("f_link_man")
    private String linkMan;

    @ApiModelProperty(value = "单位负责人电话")
    @TableField("f_link_tel")
    private String linkTel;

    @ApiModelProperty(value = "财务负责人")
    @TableField("f_fin_mgr")
    private String finMgr;

    @ApiModelProperty(value = "财务负责人电话")
    @TableField("f_fin_mgr_tel")
    private String finMgrTel;

    @ApiModelProperty(value = "行政区划省份编码")
    @TableField("f_province_id")
    private String provinceId;

    @ApiModelProperty(value = "行政区划地市编码")
    @TableField("f_city_id")
    private String cityId;

    @ApiModelProperty(value = "行政区划区县编码")
    @TableField("f_county_id")
    private String countyId;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
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

    @ApiModelProperty(value = "部门名称")
    @TableField("f_dept_name")
    private String deptName;


    public static final String F_ID = "f_id";

    public static final String F_RGN_ID = "f_rgn_id";

    public static final String F_AGEN_CODE = "f_agen_code";

    public static final String F_EFF_DATE = "f_eff_date";

    public static final String F_EXP_DATE = "f_exp_date";

    public static final String F_AGEN_NAME = "f_agen_name";

    public static final String F_MNEM = "f_mnem";

    public static final String F_LEVEL = "f_level";

    public static final String F_PID_CODE = "f_pid_code";

    public static final String F_ISLEAF = "f_isleaf";

    public static final String F_ORG_CODE = "f_org_code";

    public static final String F_DEPT_CODE = "f_dept_code";

    public static final String F_FINDEPT_ID = "f_findept_id";

    public static final String F_IND_CODE = "f_ind_code";

    public static final String F_SORT_CODE = "f_sort_code";

    public static final String F_TYPE_CODE = "f_type_code";

    public static final String F_ISENABLE = "f_isenable";

    public static final String F_ISTICK_AGEN = "f_istick_agen";

    public static final String F_ISUNPAID = "f_isunpaid";

    public static final String F_ISALARM_AGEN = "f_isalarm_agen";

    public static final String F_ADDR = "f_addr";

    public static final String F_ZIP = "f_zip";

    public static final String F_LINK_MAN = "f_link_man";

    public static final String F_LINK_TEL = "f_link_tel";

    public static final String F_FIN_MGR = "f_fin_mgr";

    public static final String F_FIN_MGR_TEL = "f_fin_mgr_tel";

    public static final String F_PROVINCE_ID = "f_province_id";

    public static final String F_CITY_ID = "f_city_id";

    public static final String F_COUNTY_ID = "f_county_id";

    public static final String F_OPERATOR = "f_operator";

    public static final String F_OPERATOR_ID = "f_operator_id";

    public static final String F_CREATE_TIME = "f_create_time";

    public static final String F_UPDATE_TIME = "f_update_time";

    public static final String F_LOGIC_DELETE = "f_logic_delete";

    public static final String F_VERSION = "f_version";

    public static final String F_NOTE = "f_note";

    public static final String F_DEPT_NAME = "f_dept_name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
