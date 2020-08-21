package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CbillAccountingDTO
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 13:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="CbillAccountingDTO对象", description="入账基础数据")
public class CbillAccountingDTO {

    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "单位代码")
    private String agenIdcode;

    @ApiModelProperty(value = "单位名称")
    private String agenName;

    @ApiModelProperty(value = "开票点id")
    private String placeId;

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

    @ApiModelProperty(value = "入账时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @ApiModelProperty(value = "入账金额")
    private BigDecimal account;

    @ApiModelProperty(value = "入账凭证号")
    @JsonSerialize(using = ToStringSerializer.class)
    private long accountId;

    @ApiModelProperty(value = "版本号")
    @Version
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss",timezone = "GMT+8",locale="zh")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss",timezone = "GMT+8",locale="zh")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态(1.已经删除 0.未删除)")
    @TableField(select = false)
    private Boolean logicDelete;

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
