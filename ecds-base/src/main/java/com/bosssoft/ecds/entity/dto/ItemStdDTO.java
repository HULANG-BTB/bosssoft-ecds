package com.bosssoft.ecds.entity.dto;

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
@Accessors(chain = true)
@TableName("fab_item_std")
@ApiModel(value="ItemStdDTO对象", description="")
public class ItemStdDTO {
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标准编码")
    @TableField("f_itemstd_code")
    private String itemstdCode;

    @ApiModelProperty(value = "标准名称")
    @TableField("f_itemstd_name")
    private String itemstdName;

    @ApiModelProperty(value = "助记码")
    @TableField("f_mnem")
    private String mnem;

    @ApiModelProperty(value = "项目编码")
    @TableField("f_item_code")
    private String itemCode;

    @ApiModelProperty(value = "标准上限")
    @TableField("f_max_charge")
    private BigDecimal maxCharge;

    @ApiModelProperty(value = "标准下限")
    @TableField("f_min_charge")
    private BigDecimal minCharge;

    @ApiModelProperty(value = "计量单位")
    @TableField("f_units")
    private String units;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Boolean isenable;

    @ApiModelProperty(value = "标准生效日期")
    @TableField("f_itemstd_effdate")
    private Date itemstdEffdate;

    @ApiModelProperty(value = "标准失效日期")
    @TableField("f_itemstd_expdate")
    private Date itemstdExpdate;

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
}
