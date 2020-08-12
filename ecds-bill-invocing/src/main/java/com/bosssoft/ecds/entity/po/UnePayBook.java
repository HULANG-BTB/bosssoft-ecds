package com.bosssoft.ecds.entity.po;

import lombok.Data;
import java.util.Date;

@Data
public class UnePayBook {
    private long fId;

    //开票单位编码
    private String fAgenidCode;

    //开票单位名称
    private String fAgenName;

    //开票点id
    private short fPlaceId;

    //开票点代码
    private String fPlaceCode;

    //开票点名称
    private String fPlaceName;

    //开具缴款书日期
    private Date fDate;

    //编制人
    private String fAuther;

    //对应票据
    private String fBillId;

    //缴款人
    private String fPayerName;

    //个人
    private int fRecAccType;

    //最后缴款日期
    private String fFinalDate;
    
}
