package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/14
 * @description
 */
@Data
@ApiModel(value="GroupDTO对象", description="")
public class GroupDTO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "单位编码")
    private String agenCode;


    @ApiModelProperty(value = "分组编码")
    private String groupCode;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

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
