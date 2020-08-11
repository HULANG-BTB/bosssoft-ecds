package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author asus
 * @create 9/8/2020 下午4:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FabAgenDTO对象", description="")
public class AgenDTO extends Model<AgenDTO> {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "区划ID")
    private String rgnId;

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "生效日期")
    private Date effDate;

    @ApiModelProperty(value = "失效日期")
    private Date expDate;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "助记码")
    private String mnem;

    @ApiModelProperty(value = "级次")
    private Integer level;

    @ApiModelProperty(value = "父级识别码")
    private String pidCode;

    @ApiModelProperty(value = "是否底级")
    private Boolean isleaf;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

    @ApiModelProperty(value = "主管部门编码")
    private String deptCode;

    @ApiModelProperty(value = "归口财政部门ID")
    private Long findeptId;

    @ApiModelProperty(value = "所属行业代码")
    private String indCode;

    @ApiModelProperty(value = "单位分类代码")
    private String sortCode;

    @ApiModelProperty(value = "单位类型代码")
    private String typeCode;

    @ApiModelProperty(value = "是否启用")
    private Boolean isenable;

    @ApiModelProperty(value = "是否开票单位")
    private Boolean istickAgen;

    @ApiModelProperty(value = "是否欠缴")
    private Boolean isunpaid;

    @ApiModelProperty(value = "是否预警单位")
    private Boolean isalarmAgen;

    @ApiModelProperty(value = "地址")
    private String addr;

    @ApiModelProperty(value = "邮政编码")
    private String zip;

    @ApiModelProperty(value = "单位负责人")
    private String linkMan;

    @ApiModelProperty(value = "单位负责人电话")
    private String linkTel;

    @ApiModelProperty(value = "财务负责人")
    private String finMgr;

    @ApiModelProperty(value = "财务负责人电话")
    private String finMgrTel;

    @ApiModelProperty(value = "行政区划省份编码")
    private String provinceId;

    @ApiModelProperty(value = "行政区划地市编码")
    private String cityId;

    @ApiModelProperty(value = "行政区划区县编码")
    private String countyId;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean logicDelete;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String note;

}
