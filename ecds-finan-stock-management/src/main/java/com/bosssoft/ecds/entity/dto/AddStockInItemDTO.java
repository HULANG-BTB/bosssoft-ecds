package com.bosssoft.ecds.entity.dto;

import lombok.Data;

/**
 * 用于创建新入库明细
 *
 * @author cheng
 * @Date 2020/8/11 15:14
 */
@Data
public class AddStockInItemDTO {
    
    /**
     * 序号
     */
    private Long no;
    
    /**
     * 编码
     */
    private String billCode;
    
    /**
     * 票据名
     */
    private String billName;
    
    /**
     * 数量
     */
    private Integer number;
    
    /**
     * 起始号
     */
    private String billNo1;
    
    /**
     * 终止号
     */
    private String billNo2;
}
