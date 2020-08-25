package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 审核状态传入对象
 *
 * @author cheng
 * @Date 2020/8/11 16:14
 */
@Data
public class StockChangeDTO {
    /**
     * 入库单号
     */
    private Long id;
    
    /**
     * 审核日期
     */
    private Date changeDate;
    
    /**
     * 审核人
     */
    private String changeMan;
    
    /**
     * 审核状态
     */
    private Integer changeState;
    
    /**
     * 审核意见
     */
    private String changeSitu;
}
