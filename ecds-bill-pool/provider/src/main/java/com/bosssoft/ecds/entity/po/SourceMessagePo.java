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

    @Override
    public String toString() {
        return "SourceMessagePo{" +
                "billTypeCode='" + billTypeCode + '\'' +
                ", table='" + table + '\'' +
                ", threshold=" + threshold +
                '}';
    }
}
