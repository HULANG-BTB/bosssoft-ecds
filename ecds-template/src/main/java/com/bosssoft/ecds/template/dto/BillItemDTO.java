package com.bosssoft.ecds.template.dto;

import lombok.Data;

/**
 * 收费项目 DTO
 */
@Data
public class BillItemDTO {

    /** 项目编码 */
    private String itemCode;

    /** 项目名称 */
    private String itemName;

    /** 单位 */
    private String units;

    /** 数量 */
    private String quantity;

    /** 标准 */
    private String standardName;

    /** 金额 */
    private String amount;
}
