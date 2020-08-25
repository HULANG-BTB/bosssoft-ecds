package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用于审核查看的
 *
 * @author cheng
 * @Date 2020/8/11 14:53
 */
@Data
public class StockInForChangeVO {
    private Long no;
    private Long warehouseId;
    private Date date;
    private String memo;
}
