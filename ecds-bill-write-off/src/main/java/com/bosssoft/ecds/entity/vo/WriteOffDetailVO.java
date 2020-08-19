package com.bosssoft.ecds.entity.vo;

import java.math.BigDecimal;

public class WriteOffDetailVO {

    /** 详细信息 */
    //开票单位
    private String fAgenIdCode;

    //票据编码
    private String fBillId;

    //票据名称
    private String fBillName;

    //计量单位
    private String fUnits;

    //份数
    private int fTotalNumber;

    //开票份数
    private int fNumber;

    //开票金额
    private BigDecimal fAmt;

    //作废份数
    private int fCancelNumber;

    // 收费项目编码
    private String fItemCode;

    // 收费项目名称
    private String fItemName;

    // 票据批次编码
    private String fBillBatchCode;

    // 起始号

    // 终止号

    /** 预警 */
    // 预警日期

    // 发送时间

    // 监控事件

    // 预警内容

    // 处理状态
}
