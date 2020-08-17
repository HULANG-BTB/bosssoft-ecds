package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AccIntoInfoVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:47
 * @Version 1.0
 */
@Data
@ApiModel(value="AccIntoInfoVO对象", description="")
public class AccIntoInfoVO {
    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "入账金额")
    private BigDecimal account;

    @ApiModelProperty(value = "入账类型")
    private int accountType;

    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;

    @ApiModelProperty(value = "入账时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}
