package com.bosssoft.ecds.entity.po;
import lombok.Data;
import java.util.Date;

@Data
public class UneCbill {

    private long fId;

    private String fRgnCode;

    private String fAgenIdCode;

    private String fPlaceId;

    private String fPlaceCode;

    private String fPlaceName;

    private String fPayerName;

    private String fPayerTel;

    private String fPayerEmail;

    private Date fDate;

    private String fAuthor;

    private String fType;

    private String fTypeCode;

    private String fBillid;

    private String fBillBatchCode;

    private String fBillNo;

    private String fMemo;

    private String fCheckCode;

    private String fTotalAmt;

    private String fState;

    private String fVersion;

    private Date fCreateTime;

    private Date fUpdateTime;

    private String fOperator;

    private long signId;

    private String fPayCode;

    private int fPayerType;

}
