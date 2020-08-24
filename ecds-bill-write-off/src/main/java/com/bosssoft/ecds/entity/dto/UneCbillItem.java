package com.bosssoft.ecds.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("une_cbill_item")
public class UneCbillItem {
    /**
     * 项目编码
     */
    private String fitemCode;

    /**
     * 项目名称
     */
    private String fitemName;

    /**
     * 单位
     */
    private String funits;

    /**
     * 金额
     */
    private double famt;
}
