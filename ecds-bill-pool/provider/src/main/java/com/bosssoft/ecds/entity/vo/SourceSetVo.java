package com.bosssoft.ecds.entity.vo;

public class SourceSetVo {

    String regionCode;
    int pushNumber;
    int minNumber;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public int getPushNumber() {
        return pushNumber;
    }

    public void setPushNumber(int pushNumber) {
        this.pushNumber = pushNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    @Override
    public String toString() {
        return "SourceSetVo{" +
                "regionCode='" + regionCode + '\'' +
                ", pushNumber=" + pushNumber +
                ", minNumber=" + minNumber +
                '}';
    }
}
