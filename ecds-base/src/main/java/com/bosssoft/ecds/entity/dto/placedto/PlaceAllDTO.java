package com.bosssoft.ecds.entity.dto.placedto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/11
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PlaceDTO对象", description="")
public class PlaceAllDTO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "区划ID")
    private String rgnId;

    @ApiModelProperty(value = "开票点编码")
    private String placeId;

    @ApiModelProperty(value = "开票点名称")
    private String placeName;

    @ApiModelProperty(value = "上级单位编码")
    private String agenId;

    @ApiModelProperty(value = "助记码")
    private String mmen;

    @ApiModelProperty(value = "级次")
    private Integer level;

    @ApiModelProperty(value = "是否启用")
    private Boolean isenable;

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
