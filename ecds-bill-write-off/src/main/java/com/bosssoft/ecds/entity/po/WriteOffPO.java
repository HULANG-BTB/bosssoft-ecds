package com.bosssoft.ecds.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class WriteOffPO {

    private long fId;

    private String fRgnCode;

    private String fNo;

    private String fAgenIdCode;

    private Date fCheckDate;

    private String fAuthor;

    private Date fDate;

    private short fCheckResult;

    private String fMemo;

    private int fVersion;

    private Date fCreateTime;

    private Date fUpdateTime;

    private Date fChangeState;

    private String fApplyId;

    private int fBatchNo;

    private long fOperatorId;

    private String fOperator;

}
