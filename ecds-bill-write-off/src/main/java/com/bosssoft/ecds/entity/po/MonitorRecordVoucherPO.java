package com.bosssoft.ecds.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class MonitorRecordVoucherPO {

    private long fId;

    private String fAgenIdCode;

    private Date fMntTime;

    private String fMntCount;

    private String fBillCode;

    private String fBillNo;

    private String fEvtName;

    private short fIsSave;

    private Date fCreateTime;

    private Date fUpdateTime;

    private long fOperatorId;

    private String fOperator;

    private int fVersion;

    private short fLogicDelete;
}
