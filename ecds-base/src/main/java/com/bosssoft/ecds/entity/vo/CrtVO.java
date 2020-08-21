package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author asus
 * @create 10/8/2020 上午9:07
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UabCrtVO对象", description="")
public class CrtVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "单位编码")
    private String agenCode;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "准购证号")
    private String crtCode;

    @ApiModelProperty(value = "准购证名称")
    private String crtName;

    @ApiModelProperty(value = "办证日期")
    private Date issuedate;

    @ApiModelProperty(value = "单位法人证号")
    private String legalno;

    @ApiModelProperty(value = "收费许可证号")
    private String chargno;

    @ApiModelProperty(value = "罚没许可证号")
    private String fineno;

    @ApiModelProperty(value = "收费委托书号")
    private String proxyno;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "单位负责人")
    private String linkman;

    @ApiModelProperty(value = "单位负责人电话")
    private String linkmanTel;

    @ApiModelProperty(value = "财务负责人")
    private String finmgr;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean logicDelete;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "是否启用")
    private Boolean isenable;

}
