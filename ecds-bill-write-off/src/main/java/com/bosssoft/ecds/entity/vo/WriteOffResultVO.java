package com.bosssoft.ecds.entity.vo;

public class WriteOffResultVO {
    /**
     * 单位识别码
     * */
    private String fAgenIdCode;

    /**
     * 业务单号
     */
    private String fNo;

    /**
     * 审核结果
     * */
    private String res;

    public String getfAgenIdCode() {
        return fAgenIdCode;
    }

    public void setfAgenIdCode(String fAgenIdCode) {
        this.fAgenIdCode = fAgenIdCode;
    }

    public String getfNo() {
        return fNo;
    }

    public void setfNo(String fNo) {
        this.fNo = fNo;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
