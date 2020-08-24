package com.bosssoft.ecds.entity.dto;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/20 11:47
 */
public class RequestBillDto {

    private String billPrecode;
    private Integer number;
    private String useMan;
    private String author;

    public RequestBillDto(String billPrecode, Integer number, String useMan, String author) {

        this.billPrecode = billPrecode;
        this.number = number;
        this.useMan = useMan;
        this.author = author;
    }

    public String getBillPrecode() {
        return billPrecode;
    }

    public void setBillPrecode(String billPrecode) {
        this.billPrecode = billPrecode;
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
                "billPrecode='" + billPrecode + '\'' +
                ", number=" + number +
                ", useMan='" + useMan + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
