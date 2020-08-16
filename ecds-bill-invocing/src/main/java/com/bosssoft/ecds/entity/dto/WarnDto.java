package com.bosssoft.ecds.entity.dto;

import lombok.Data;
import java.util.Date;

@Data
public class WarnDto {
    /**
     * 单位编码
     */
    private String fAgenIdCode;

    /**
     * 票据代码
     */
    private String fBillId;

    /**
     *  票据号码
     */
    private String fBillNo;

    /**
     * 预警发生时间
     */
    private Date mntTime;

    /**
     * 预警事件名称
     */
    private String evtName;

    /**
     * 预警内容
     */
    private String mntCont;
}
