package com.bosssoft.ecds.pay.domain.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AccIntoInfoDto
 * @Description 缴款信息Dto
 * @auther wangpeng
 * @Date 2020/8/20 9:08
 * @Version 1.0
 **/
@Data
@ApiModel(value = "AccIntoInfoDto",description = "缴款信息提交Dto")
public class AccIntoInfoDto {

    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "入账金额")
    private BigDecimal account;

    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;

    @ApiModelProperty(value = "入账类型")
    private int accountType;

    @ApiModelProperty(value = "入账时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}
