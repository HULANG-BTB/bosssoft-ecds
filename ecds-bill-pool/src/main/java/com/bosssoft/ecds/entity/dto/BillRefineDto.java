package com.bosssoft.ecds.entity.dto;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/21 17:32
 */
public class BillRefineDto {

    private List<InsertBillDto> data;

    public List<InsertBillDto> getData() {
        return data;
    }

    public void setData(List<InsertBillDto> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BillRefineDto{" +
                "data=" + data +
                '}';
    }
}
