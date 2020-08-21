package com.bosssoft.ecds.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UnePayBook {

    @TableId(value = "f_id")
    private int fId;

    //区划编码
    private String fRgnCode;

    //单位识别码
    private String fAgenidCode;

    //单位名称
    private String fAgenName;

    //开票点ID
    private short fPlaceId;

    //开票点编码
    private String fPlaceCode;

    //开票点名称
    private String fPlaceName;

    //开票日期
    private Date fDate;

    //编制人
    private String fAuther;

    //票据ID
    private String fBillId;

    //票据批次号
    private String fBillBatchCode;

    //票据序号
    private String fBillNo;

    //备注
    private String fMemo;

    //缴款人ID
    private String fPayerId;

    //缴款人姓名
    private String fPayerName;

    //缴款人电话
    private String fPayerTel;

    //账户类型
    private int fRecAccType;

    //校验码
    private String fCheckCode;

    //操作人
    private String fOperator;

    //缴款书票据ID
    private String fPayBillId;

    //缴款书票据编码
    private String fPayBillBatchCode;

    //缴款书票据序号
    private String fpaybillNo;

    //缴款人邮箱
    private String fPayerEmail;

}
