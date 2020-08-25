package com.bosssoft.ecds.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@ApiModel(value="AccBaseInfoVO对象", description="")
public class AccBaseInfoVO {

    @NotBlank(message = "单位代码不能为空")
    @ApiModelProperty(value = "单位代码")
    private String fAgenIdCode;

    @NotBlank(message = "单位名称不能为空")
    @ApiModelProperty(value = "单位名称")
    private String fPlaceName;

    @NotBlank(message = "票据校验码不能为空")
    @ApiModelProperty(value = "票据校验码")
    private String fCheckCode;

    @NotBlank(message = "开票地点不能为空")
    @ApiModelProperty(value = "开票地点id")
    private String fPlaceId;

    @NotBlank(message = "待交款金额不能为空")
    @Min(value=0,message = "金额必须大于0")
    @ApiModelProperty(value = "待缴款金额")
    private double fTotalAmt;

    @NotBlank(message = "缴款人电话不能为空")
    @Size(min=4, max=11,message = "电话必须为4-11位")
    @ApiModelProperty(value = "缴款人电话")
    private String fPayerTel;
}
