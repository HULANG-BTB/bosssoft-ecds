package com.bosssoft.ecds.entity.dto;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/20 12:58
 */
public class InsertBillDto {

    private String billPreCode;
    private Integer number;
    private String billNo1;
    private String billNo2;
    private String useMan;
    private String author;

    public String getBillPreCode() {
        return billPreCode;
    }

    public void setBillPreCode(String billPreCode) {
        this.billPreCode = billPreCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBillNo1() {
        return billNo1;
    }

    public void setBillNo1(String billNo1) {
        this.billNo1 = billNo1;
    }

    public String getBillNo2() {
        return billNo2;
    }

    public void setBillNo2(String billNo2) {
        this.billNo2 = billNo2;
    }

    public String getUseMan() {
        return useMan;
    }

    public void setUseMan(String useMan) {
        this.useMan = useMan;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "InsertBillDto{" +
                "billPreCode='" + billPreCode + '\'' +
                ", number=" + number +
                ", billNo1='" + billNo1 + '\'' +
                ", billNo2='" + billNo2 + '\'' +
                ", useMan='" + useMan + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
