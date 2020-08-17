package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WriteOffDetailDTO {

    // 审验单ID
    private String fPid;

    //审验顺序-序号
    private Integer fSortNo;

    //票据编码
    private String fBillCode;

    //票据名称
    private String fBillName;

    //票据ID
    private String fBillId;

    //票据代码
    private String fBatchCode;

    //数量
    private Integer fNumber;

    //票据起始号
    private String fBillNo1;

    //票据结束号
    private String fBillNo2;

    //票面金额
    private BigDecimal fAmt;

    //开票份数
    private Integer fInvNum;

    //版本号
    private Integer fVersion;

    //创建时间
    private Date fCreateTime;

    //修改时间
    private Date fUpdateTime;

    //操作人id
    private Long fOperatorId;

    //操作人
    private String fOperator;
}
