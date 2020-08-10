package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/9
 * @description
 */
@Data
@TableName("fab_item")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ItemVO对象", description="")
public class ItemVO {
    @ApiModelProperty(value = "主键")
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区划id")
    @TableField("f_rgn_id")
    private String rgnId;

    @ApiModelProperty(value = "项目编码")
    @TableField("f_item_id")
    private String itemId;

    @ApiModelProperty(value = "项目名称")
    @TableField("f_item_name")
    private String itemName;

    @ApiModelProperty(value = "项目分类编码")
    @TableField("f_item_sort_id")
    private String itemSortId;

    @ApiModelProperty(value = "助记码")
    @TableField("f_mnen")
    private String mnen;

    @ApiModelProperty(value = "项目生效日期")
    @TableField("f_item_effdate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date itemEffdate;

    @ApiModelProperty(value = "项目失效日期")
    @TableField("f_item_expdate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date itemExpdate;

    @ApiModelProperty(value = "记录生效日期")
    @TableField("f_effdate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date effdate;

    @ApiModelProperty(value = "记录截止日期")
    @TableField("f_expdate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date expdate;

    @ApiModelProperty(value = "是否启用")
    @TableField("f_isenable")
    private Integer isenable;

    @ApiModelProperty(value = "收入类别")
    @TableField("f_incom_sort_code")
    private String incomSortCode;

    @ApiModelProperty(value = "资金性质")
    @TableField("f_fundsnature_code")
    private String fundsnatureCode;

    @ApiModelProperty(value = "预算科目")
    @TableField("f_subject")
    private String subject;

    @ApiModelProperty(value = "收缴方式")
    @TableField("f_paymode")
    private String paymode;

    @ApiModelProperty(value = "经办人")
    @TableField(value = "f_operator", fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @ApiModelProperty(value = "经办人id")
    @TableField(value = "f_operator_id", fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("f_logic_delete")
    @TableLogic
    private Integer logicDelete;

    @ApiModelProperty(value = "版本号")
    @TableField("f_version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "备注")
    @TableField("f_note")
    private String note;
}
