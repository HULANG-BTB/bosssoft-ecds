package com.bosssoft.ecds.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @ClassName FneCbillAccountingVO
 * @Description FneCbillAccountingVO
 * @auther wangpeng
 * @Date 2020/8/19 15:46
 * @Version 1.0
 **/
@Data
public class FneCbillAccountingVO {

    /**
     * "单位代码"
     **/
    private String fAgenIdcode;

    /**
     * "单位名称"
     **/
    private String fAgenName;

    /**
     * "开票时间"
     **/
    private Date fAgenTime;

    /**
     * "入账单位类型，开票单位(值=0) 缴款单位(值=1)"
     **/
    private Integer fType;

    /**
     * "票据批次号"
     **/
    private String fBillBatchId;

    /**
     * "票据号码"
     **/
    private String fBillNo;

    /**
     * "入账时间"
     **/
    private Date fTime;

    /**
     * "入账金额"
     **/
    private BigDecimal fAccount;

    /**
     * "入账凭证号"
     **/
    private BigInteger fAccountId;

    /**
     * "应缴费金额"
     **/
    private BigDecimal fWaitAccount;

    /**
     * "入账方式"
     **/
    private Integer fAccountType;

    /**
     * "入账完成状态（0.未完成 1.已完成）"
     **/
    private Integer fAccountStatus;

    /**
     * "缴款人电话"
     **/
    private String fPayerTel;

}
