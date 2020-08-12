package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("une_cbill")
public class UneCbill {

    /**
     * 开票主表
     */
    @TableId(value = "f_id")
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

    private String fBillId;

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

    private long fSignId;

    private String fPayCode;

    private int fPayerType;

}
