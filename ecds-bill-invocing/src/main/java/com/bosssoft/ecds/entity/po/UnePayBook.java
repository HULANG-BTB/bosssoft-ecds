package com.bosssoft.ecds.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class UnePayBook {
    private int fId;

    private String fAgenidCode;

    private String fAgenName;

    private short fPlaceId;

    private String fPlaceCode;

    private String fPlaceName;

    private Date fDate;

    private String fAuther;

    private String fBillId;

    private String fPayerName;

    private int fRecAccType;

    private String fFinalDate;
    
}
