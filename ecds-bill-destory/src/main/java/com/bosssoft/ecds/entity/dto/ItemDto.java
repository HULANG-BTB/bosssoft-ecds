package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: qiuheng
 * @create: 2020-08-12 17:22
 **/
public class ItemDto implements Serializable {
    /**
     * 票据代码
     */
    private String fBillBatchCode;

    /**
     * 仓库ID
     */
    private String fWarehouseId;

    /**
     * 仓库名
     */
    private String fWarehouseName;

    /**
     * 数量
     */
    private Integer fNumber;

    /**
     * 起始号
     */
    private String fBillNo1;

    /**
     * 终止号
     */
    private String fBillNo2;

    public String getfBillBatchCode() {
        return fBillBatchCode;
    }

    public void setfBillBatchCode(String fBillBatchCode) {
        this.fBillBatchCode = fBillBatchCode;
    }

    public String getfWarehouseId() {
        return fWarehouseId;
    }

    public void setfWarehouseId(String fWarehouseId) {
        this.fWarehouseId = fWarehouseId;
    }

    public String getfWarehouseName() {
        return fWarehouseName;
    }

    public void setfWarehouseName(String fWarehouseName) {
        this.fWarehouseName = fWarehouseName;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public String getfBillNo1() {
        return fBillNo1;
    }

    public void setfBillNo1(String fBillNo1) {
        this.fBillNo1 = fBillNo1;
    }

    public String getfBillNo2() {
        return fBillNo2;
    }

    public void setfBillNo2(String fBillNo2) {
        this.fBillNo2 = fBillNo2;
    }
}
