package com.bosssoft.ecds.entity.vo;

import java.util.Date;

public class SearchFromVo {

    // 业务单号
    private long number;

    // 起始日期
    private Date date1;

    // 结束日期
    private Date date2;

    // 状态
    private short state;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }
}
