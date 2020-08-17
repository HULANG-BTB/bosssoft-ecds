package com.bosssoft.ecds.entity.dto.itemdto;

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
@ApiModel(value="ItemDTO对象", description="")
public class ItemDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "区划id")
    private String rgnId;

    @ApiModelProperty(value = "项目编码")
    private String itemId;

    @ApiModelProperty(value = "项目名称")
    private String itemName;

    @ApiModelProperty(value = "项目分类编码")
    private String itemSortId;

    @ApiModelProperty(value = "助记码")
    private String mnen;

    @ApiModelProperty(value = "项目生效日期")
    private Date itemEffdate;

    @ApiModelProperty(value = "项目失效日期")
    private Date itemExpdate;

    @ApiModelProperty(value = "记录生效日期")
    private Date effdate;

    @ApiModelProperty(value = "记录截止日期")
    private Date expdate;

    @ApiModelProperty(value = "是否启用")
    private Integer isenable;

    @ApiModelProperty(value = "收入类别")
    private String incomSortCode;

    @ApiModelProperty(value = "资金性质")
    private String fundsnatureCode;

    @ApiModelProperty(value = "预算科目")
    private String subject;

    @ApiModelProperty(value = "收缴方式")
    private String paymode;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "经办人id")
    private Long operatorId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String note;
}
