package com.bosssoft.ecds.entity.pojo;

import com.bosssoft.ecds.entity.dto.PayerDto;
import com.bosssoft.ecds.entity.dto.UneCbillDto;
import com.bosssoft.ecds.entity.dto.UneCbillItemDto;
import java.util.List;

public class BatchPojo {
    private String unitName;

    private PayerDto payerDto;

    private UneCbillDto uneCbillDto;

    List<UneCbillItemDto> itemDtos;

    private double amt;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public PayerDto getPayerDto() {
        return payerDto;
    }

    public void setPayerDto(PayerDto payerDto) {
        this.payerDto = payerDto;
    }

    public UneCbillDto getUneCbillDto() {
        return uneCbillDto;
    }

    public void setUneCbillDto(UneCbillDto uneCbillDto) {
        this.uneCbillDto = uneCbillDto;
    }

    public List<UneCbillItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<UneCbillItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
}
