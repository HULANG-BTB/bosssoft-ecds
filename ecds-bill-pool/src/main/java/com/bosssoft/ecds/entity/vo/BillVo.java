package com.bosssoft.ecds.entity.vo;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public class BillVo {

    private String regionCode;
    private String type;
    private String sort;
    private String batch;
    private String billCode;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Override
    public String toString() {
        return "BillVo{" +
                "regionCode='" + regionCode + '\'' +
                ", type='" + type + '\'' +
                ", sort='" + sort + '\'' +
                ", batch='" + batch + '\'' +
                ", billCode='" + billCode + '\'' +
                '}';
    }
}
