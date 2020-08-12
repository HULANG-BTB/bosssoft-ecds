package com.bosssoft.ecds.entity.dto;

import lombok.Data;

/**
 * @author cheng
 * @Date 2020/8/11 13:48
 */
@Data
public class AddStockInDTO {
    /**
     * 业务号
     */
    private Long no;
    
    /**
     * 仓库号
     */
    private Long warehouseId;
    
    /**
     * 编制者
     */
    private String author;
    
    /**
     * 备注
     */
    private String memo;
    
    /**
     * 票据来源
     */
    private String billSource;
    
    /**
     * 明细列表
     */
    private AddStockInItemDTO[] addStockInItemDTOArray;
}
