package com.bosssoft.ecds.entity.vo.itemvo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/10
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ItemStdVO对象", description = "")
public class ItemStdVO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "标准编码")
    private String itemstdCode;

    @ApiModelProperty(value = "标准名称")
    private String itemstdName;

    @ApiModelProperty(value = "助记码")
    private String mnem;

    @ApiModelProperty(value = "项目编码")
    private String itemCode;

    @ApiModelProperty(value = "标准上限")
    private BigDecimal maxCharge;

    @ApiModelProperty(value = "标准下限")
    private BigDecimal minCharge;

    @ApiModelProperty(value = "标准金额")
    private BigDecimal charge;

    @ApiModelProperty(value = "计量单位")
    private String units;

    @ApiModelProperty(value = "是否启用")
    private Integer isenable;

    @ApiModelProperty(value = "标准生效日期")
    private Date itemstdEffdate;

    @ApiModelProperty(value = "标准失效日期")
    private Date itemstdExpdate;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人ID")
    private Long operatorId;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String note;
}
