package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("归档总览信息dto")
public class OverViewArchiveDto implements Serializable {
    @ApiModelProperty("显示id或者说显示行号")
    private Long id;

    @ApiModelProperty("公司编码")
    private String agenIdCode;

    @ApiModelProperty("单位名称")
    private String agenName;

    @ApiModelProperty(value = "单位分类")
    private String kindName;

    @ApiModelProperty(value = "单位地址")
    private String linkAddr;

    @ApiModelProperty(value = "票据领用数量")
    private Long applyNumber;

    @ApiModelProperty(value = "票据使用数量")
    private Long useNumber;

    @ApiModelProperty(value = "票据已审核数量")
    private Long authorNumber;

    @ApiModelProperty(value = "票据未审核数量")
    private Long unauthorNumber;

    @ApiModelProperty(value = "票据预警票数")
    private Long warnNumber;

    @ApiModelProperty(value = "票据退票数量")
    private Long returnNumber;

    @ApiModelProperty(value = "票据销毁数量")
    private Long destoryNumber;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "操作人")
    private String operator;
}
