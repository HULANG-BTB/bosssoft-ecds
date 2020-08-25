package com.bosssoft.ecds.entity.vo;

import com.bosssoft.ecds.entity.dto.AddStockInItemDTO;
import lombok.Data;

/**
 * 前端显示入库单完整信息的VO，用于修改和查看入库单时的回显
 *
 * @author cheng
 * @Date 2020/8/15 10:44
 */
@Data
public class StockInInfo {
    /**
     * 业务单号
     */
    private Long no;
    
    /**
     * 备注
     */
    private String memo;
    
    /**
     * 入库明细列表
     */
    private AddStockInItemDTO[] addStockInItemDTOArray;
}
