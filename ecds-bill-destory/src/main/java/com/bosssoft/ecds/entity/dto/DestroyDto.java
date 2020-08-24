package com.bosssoft.ecds.entity.dto;

/**
 * @author: qiuheng
 * @create: 2020-08-23 16:34
 **/
public class DestroyDto {
    private String fAgenIdCode;

    private String fBillBatchCode;

    private String fWarehouseId;

    private long fBillNo1;

    private long fBillNo2;

    public String getfAgenIdCode() {
        return fAgenIdCode;
    }

    public void setfAgenIdCode(String fAgenIdCode) {
        this.fAgenIdCode = fAgenIdCode;
    }

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

    public long getfBillNo1() {
        return fBillNo1;
    }

    public void setfBillNo1(long fBillNo1) {
        this.fBillNo1 = fBillNo1;
    }

    public long getfBillNo2() {
        return fBillNo2;
    }

    public void setfBillNo2(long fBillNo2) {
        this.fBillNo2 = fBillNo2;
    }
}
