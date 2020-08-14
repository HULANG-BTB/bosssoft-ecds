package com.bosssoft.ecds.entity.po;

public class SourceMessagePo {

    private String regionCode;
    private String table;
    private int threshold;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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
                "regionCode='" + regionCode + '\'' +
                ", table='" + table + '\'' +
                ", threshold=" + threshold +
                '}';
    }
}
