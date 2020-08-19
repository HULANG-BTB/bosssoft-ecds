package com.bosssoft.ecds.entity.dto.agendto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 吴志鸿
 * @date 2020/8/12
 * @description
 */
@Data
@ApiModel(value = "AgenBillDTO对象", description = "")
public class AgenBillDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "单位编码")
    private String agenIdcode;

    @ApiModelProperty(value = "票据编码")
    private String typeCode;

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
