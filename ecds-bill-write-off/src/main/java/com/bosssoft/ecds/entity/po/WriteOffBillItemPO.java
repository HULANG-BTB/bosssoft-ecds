package com.bosssoft.ecds.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class WriteOffBillItemPO {

    private long fId;

    private String fPid;

    private int fSortNo;

    private String fBillCode;

    private String fBillName;

    private String fBillId;

    private String fBatchCode;

    private int fNumber;

    private String fBillNo1;

    private String fBillNo2;

    private float fAmt;

    private int fInvNum;

    private int fVersion;

    private Date fCreateTime;

    private Date fUpdateTime;

    private long fOperatorId;

    private String fOperator;
}
