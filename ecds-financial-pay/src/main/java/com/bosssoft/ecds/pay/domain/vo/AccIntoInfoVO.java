package com.bosssoft.ecds.pay.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AccIntoInfoVO
 * @Description TODO
 * @auther wangpeng
 * @Date 2020/8/24 10:48
 * @Version 1.0
 **/
@Data
@ApiModel(value="AccIntoInfoVO传输对象", description="缴款信息提交VO")
public class AccIntoInfoVO {

    @NotBlank(message = "票据校验码不能为空")
    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @NotNull(message = "入账金额不能为空")
    @DecimalMin(value="0",message = "入账金额必须大于0")
    @ApiModelProperty(value = "入账金额")
    private BigDecimal account;

    @NotNull(message = "入账类型不能为空")
    @ApiModelProperty(value = "入账类型")
    private int accountType;

    @NotBlank(message = "缴款方电话不能为空")
    @Size(min=4, max=11,message = "缴款方电话必须为4-11位")
    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;

    @NotNull(message = "入账时间不能为空")
    @ApiModelProperty(value = "入账时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}