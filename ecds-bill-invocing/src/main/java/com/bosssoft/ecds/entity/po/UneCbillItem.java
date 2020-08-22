package com.bosssoft.ecds.entity.po;

import lombok.Data;
import java.util.Date;

@Data
public class UneCbillItem {

    private long fId;

    private long fpid;

    private int fSortNo;

    private String fItemCode;

    private String fItemName;

    private String fUnits;

    private int fNumber;

    private double fStd;

    private double fAmt;

    private int fVersion;

    private Date fCreateTime;

    private Date fUpdateTime;

    private String fOperator;
}
