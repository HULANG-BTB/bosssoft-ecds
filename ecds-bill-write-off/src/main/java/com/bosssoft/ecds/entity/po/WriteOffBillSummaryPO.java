package com.bosssoft.ecds.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class WriteOffBillSummaryPO {

    private long fId;

    private String fPid;

    private int fSortNo;

    private String fBillCode;

    private String fBillName;

    private String fBillId;

    private String fBatchCode;

    private float fAmt;

    private int fNumber;

    private int fInvNum;

    private Date fCreateTime;

    private Date fUpdateTime;

    private int fVersion;

    private long fOperatorId;

    private String fOperator;
}
