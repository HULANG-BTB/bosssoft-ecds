package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

    @NotNull(message = "票据校验码不能为空")
    @ApiModelProperty(value = "票据校验码")
    private String checkCode;

    @NotNull(message = "票据序列号不能为空")
    @ApiModelProperty(value = "票据序列号")
    private String billBatchId;

    @NotNull(message = "票据号不能为空")
    @ApiModelProperty(value = "票据号")
    private String billNo;

    @NotNull(message = "开票时间不能为空")
    @Past(message = "开票时间必须为过去的时间")
    @ApiModelProperty(value = "开具票据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date = new Date();
}
