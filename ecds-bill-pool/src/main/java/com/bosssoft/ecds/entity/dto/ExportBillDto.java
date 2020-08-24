package com.bosssoft.ecds.entity.dto;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/20 13:14
 */
public class ExportBillDto {

    private String regionCode;
    private long number;
    private long billCodeBegin;
    private long billCodeEnd;

    public ExportBillDto() {

    }

    public ExportBillDto(String regionCode, long number, long billCodeBegin, long billCodeEnd) {
        this.regionCode = regionCode;
        this.number = number;
        this.billCodeBegin = billCodeBegin;
        this.billCodeEnd = billCodeEnd;
    }


    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getBillCodeBegin() {
        return billCodeBegin;
    }

    public void setBillCodeBegin(long billCodeBegin) {
        this.billCodeBegin = billCodeBegin;
    }

    public long getBillCodeEnd() {
        return billCodeEnd;
    }

    public void setBillCodeEnd(long billCodeEnd) {
        this.billCodeEnd = billCodeEnd;
    }

    @Override
    public String toString() {
        return "ExportBillDto{" +
                "regionCode='" + regionCode + '\'' +
                ", number=" + number +
                ", billCodeBegin=" + billCodeBegin +
                ", billCodeEnd=" + billCodeEnd +
                '}';
    }
}
