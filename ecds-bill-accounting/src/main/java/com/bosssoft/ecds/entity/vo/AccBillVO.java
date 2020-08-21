package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName AccBillVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:45
 * @Version 1.0
 */
@Data
@ApiModel(value="AccBillVO对象", description="")
public class AccBillVO {
    @ApiModelProperty(value = "票据校验码")
    private String checkCode;

    @ApiModelProperty(value = "票据序列号")
    private String billBatchId;

    @ApiModelProperty(value = "票据号")
    private String billNo;

    @ApiModelProperty(value = "开具票据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date = new Date();
}
