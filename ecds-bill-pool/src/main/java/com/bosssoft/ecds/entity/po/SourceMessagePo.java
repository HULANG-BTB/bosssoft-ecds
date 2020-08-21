package com.bosssoft.ecds.entity.po;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public class SourceMessagePo {

    private String billTypeCode;
    private String table;
    private int threshold;
    private int pushNumber;

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getPushNumber() {
        return pushNumber;
    }

    public void setPushNumber(int pushNumber) {
        this.pushNumber = pushNumber;
    }

    @Override
    public String toString() {
        return "SourceMessagePo{" +
                "billTypeCode='" + billTypeCode + '\'' +
                ", table='" + table + '\'' +
                ", threshold=" + threshold +
                ", pushNumber=" + pushNumber +
                '}';
    }
}
