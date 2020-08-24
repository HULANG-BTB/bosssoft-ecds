package com.bosssoft.ecds.entity.dto;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/20 11:47
 */
public class RequestBillDto {

    private String billPreCode;
    private Integer number;
    private String useMan;
    private String author;

    public RequestBillDto(String billPreCode, Integer number, String useMan, String author) {
        this.billPreCode = billPreCode;
        this.number = number;
        this.useMan = useMan;
        this.author = author;
    }

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
        return "RequestBillDto{" +
                "billPreCode='" + billPreCode + '\'' +
                ", number=" + number +
                ", useMan='" + useMan + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
