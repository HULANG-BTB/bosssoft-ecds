package com.bosssoft.ecds.entity.dto;

import com.bosssoft.ecds.entity.po.UneCbillItem;
import lombok.Data;

import java.util.List;

@Data
public class PayDto {
    /**
     * 缴款人
     */
    private String payerName;

    /***
     * 项目明细
     */
    private List<UneCbillItemDto> uneCbillItems;
}
