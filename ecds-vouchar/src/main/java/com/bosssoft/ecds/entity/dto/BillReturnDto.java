package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "BillReturnDto", description = "退票信息传输对象")
public class BillReturnDto {

    @ApiModelProperty(value = "票据批次")
    private Long billBatch;

    @ApiModelProperty(value = "票据代码")
    private String billCode;

    @ApiModelProperty(value = "票据起始号码")
    private String billNo1;

    @ApiModelProperty(value = "票据终止号码")
    private String billNo2;

    @ApiModelProperty(value = "退票数量")
    private Long number;

    @ApiModelProperty(value = "票据退票人姓名")
    private String returner;

    @ApiModelProperty(value = "票据退票时间")
    private Date date;

    @ApiModelProperty(value = "票据退票原因")
    private String returnReason;

    @ApiModelProperty(value = "退票审核时间")
    private Date changeTime;

    @ApiModelProperty(value = "退票审核人姓名")
    private String changeName;

    @ApiModelProperty(value = "退票审核状态")
    private Integer changeState;

    @ApiModelProperty(value = "退票审核意见（未通过原因）")
    private String changeSitu;
}
