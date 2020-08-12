package com.bosssoft.ecds.entity.dto;

import lombok.Data;

/**
 * 修改入库单DTO
 *
 * @author cheng
 * @Date 2020/8/12 8:45
 */
@Data
public class UpdateStockInDTO extends StockInDTO {
    /**
     * 入库单号
     */
    private Long no;
    
    /**
     * 变更人
     */
    private String changeMan;
    
    /**
     * 入库明细列表
     */
    private AddStockInItemDTO[] addStockInItemDTOArray;
}
