package com.bosssoft.ecds.template.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 中央非税收入统一票据 DTO
 */
@Data
public class NontaxBillDto {
    /**
     * 票据代码
     */
    private String billCode;

    /**
     * 票号
     */
    private String serialCode;

    /**
     * 校验码
     */
    private String checkCode;

    /**
     * 交款人
     */
    private String payerName;

    /**
     * 开票日期
     */
    private String date;

    /**
     * 金额合计（小写）
     */
    private String totalAmount;

    /**
     * 金额合计（大写）
     */
    private String totalAmountCapital;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附加说明
     */
    private String addition;

    /**
     * 收款单位
     */
    private String agenName;

    /**
     * 收款人
     */
    private String payee;

    /**
     * 复核人
     */
    private String checker;

    /**
     * 收费项目
     */
    List<BillItemDto> items;
}
