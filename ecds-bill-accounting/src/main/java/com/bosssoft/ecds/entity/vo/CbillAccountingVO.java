package com.bosssoft.ecds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CbillAccountingVO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="CbillAccountingVO对象", description="入账基础数据")
public class CbillAccountingVO {

    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "单位代码")
    @JsonSerialize(using = ToStringSerializer.class)
    private String agenIdcode;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "开票点id")
    private String placeId;

    @Past(message = "开票时间必须为过去的时间")
    @ApiModelProperty(value = "开票时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date agenTime;

    @ApiModelProperty(value = "入账单位类型(0.开票单位  1.缴款单位)")
    private Integer type;

    @ApiModelProperty(value = "票据批次号")
    private String billBatchId;

    @ApiModelProperty(value = "票据校验码")
    private String billSerialId;

    @ApiModelProperty(value = "票据号码")
    private String billNo;

    @Past(message = "入账时间必须为过去的时间")
    @ApiModelProperty(value = "入账时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @ApiModelProperty(value = "入账金额")
    private BigDecimal account;

    @ApiModelProperty(value = "入账凭证号")
    @JsonSerialize(using = ToStringSerializer.class)
    private long accountId;

    @ApiModelProperty(value = "应缴金额")
    private BigDecimal waitAccount;

    @ApiModelProperty(value = "入账方式(1.现金 2.电子 3.等等)")
    private Integer accountType;

    @ApiModelProperty(value = "入账完成状态(0.未完成 1.已完成)")
    private Boolean accountStatus;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "操作人id")
    private Long operatorId;

    @ApiModelProperty(value = "缴款人电话")
    private String payerTel;

    @ApiModelProperty(value = "备注")
    private String node;

}