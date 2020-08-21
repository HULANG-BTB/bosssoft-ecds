package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName VoucherVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="VoucherVO对象", description="入账凭证")
public class VoucherVO {
    @ApiModelProperty(value = "入账凭证号")
    private Long accountId;

    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "票据号码")
    private String billNo;

    @ApiModelProperty(value = "单位代码")
    private String agenIdcode;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "开票时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date agenTime;

    @ApiModelProperty(value = "开票点id")
    private String placeId;

    @ApiModelProperty(value = "应缴金额")
    private BigDecimal waitAccount;

    @ApiModelProperty(value = "入账金额")
    private BigDecimal account;

    @ApiModelProperty(value = "入账方式(1.现金 2.电子 3.等等)")
    private Integer accountType;

    @ApiModelProperty(value = "缴款人联系方式")
    private String payerTel;
}
