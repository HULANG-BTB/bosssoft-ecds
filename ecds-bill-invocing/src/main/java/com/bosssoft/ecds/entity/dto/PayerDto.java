package com.bosssoft.ecds.entity.dto;

public class PayerDto {
    /**
     * 缴款人
     */
    private String fPayerName;

    /**
     * 缴款人邮箱
     */
    private String fPayerEmail;

    /**
     * 缴款人号码
     */
    private String fPayerTel;

    /**
     * 缴款类型
     */
    private int fPayerType;

    /**
     * 开票备注
     */
    private String fMemo;

    public String getfPayerName() {
        return fPayerName;
    }

    public void setfPayerName(String fPayerName) {
        this.fPayerName = fPayerName;
    }

    public String getfPayerEmail() {
        return fPayerEmail;
    }

    public void setfPayerEmail(String fPayerEmail) {
        this.fPayerEmail = fPayerEmail;
    }

    public String getfPayerTel() {
        return fPayerTel;
    }

    public void setfPayerTel(String fPayerTel) {
        this.fPayerTel = fPayerTel;
    }

    public int getfPayerType() {
        return fPayerType;
    }

    public void setfPayerType(int fPayerType) {
        this.fPayerType = fPayerType;
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo;
    }
}
