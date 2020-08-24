package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDto {
    /**
     * 附加说明
     */
    private String addtion;

    /**
     *
     */
    private String agenName;

    private String billCode;

    private String checkCode;

    private String checker;

    private String date;

    private String payer;

    private String payerName;

    private String remark;

    private String serialCode;

    private String totalAmount;

    private String totalAmountCapital;

    List<UneCbillItemDto> items;
}
