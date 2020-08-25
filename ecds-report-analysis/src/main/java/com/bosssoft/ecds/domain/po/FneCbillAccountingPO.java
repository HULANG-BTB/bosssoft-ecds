package com.bosssoft.ecds.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @ClassName FneCbillAccountingPO
 * @Description 入账明细表实体类
 * @auther wangpeng
 * @Date 2020/8/19 14:31
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FneCbillAccountingPO" ,description = "入账明细表实体类" )
@TableName("fne_cbill_accounting")
public class FneCbillAccountingPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开票票据入账明细表id,主键")
    @TableId(type = IdType.AUTO)
    private BigInteger fId;

    @ApiModelProperty(value = "单位代码")
    private String fAgenIdcode;

    @ApiModelProperty(value = "单位名称")
    private String fAgenName;

    @ApiModelProperty(value = "开票点Id")
    private String fPlaceId;

    @ApiModelProperty(value = "开票时间")
    private Date fAgenTime;

    @ApiModelProperty(value = "入账单位类型，开票单位(值=0) 缴款单位(值=1)")
    private Integer fType;

    @ApiModelProperty(value = "票据批次号")
    private String fBillBatchId;

    @ApiModelProperty(value = "票据校验码")
    private String fBillSerialId;

    @ApiModelProperty(value = "票据号码")
    private String fBillNo;

    @ApiModelProperty(value = "入账时间")
    private Date fTime;

    @ApiModelProperty(value = "入账金额")
    private BigDecimal fAccount;

    @ApiModelProperty(value = "入账凭证号")
    private BigInteger fAccountId;

    @ApiModelProperty(value = "版本号(乐观锁)")
    @Version
    private Integer fVersion;

    @ApiModelProperty(value = "创建时间")
    private Date fCreateTime;

    @ApiModelProperty(value = "更新时间")
    private Date fUpdateTime;

    @ApiModelProperty(value = "删除状态（1.已经删除 0.未删除）")
    @TableLogic
    private Integer fLogicDelete;

    @ApiModelProperty(value = "应缴费金额")
    private BigDecimal fWaitAccount;

    @ApiModelProperty(value = "入账方式")
    private Integer fAccountType;

    @ApiModelProperty(value = "入账完成状态（0.未完成 1.已完成）")
    private Integer fAccountStatus;

    @ApiModelProperty(value = "操作人")
    private String fOperator;

    @ApiModelProperty(value = "操作人id")
    private BigInteger fOperatorId;

    @ApiModelProperty(value = "缴款人电话")
    private String fPayerTel;

    @ApiModelProperty(value = "备注")
    private String fNode;



}

