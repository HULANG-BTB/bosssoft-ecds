package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * 页面显示入库单列表项
 *
 * @author cheng
 * @Date 2020/8/15 14:58
 */
@Data
public class StockInListVO {
    private Long no;
    private String date;
    private String memo;
    private String author;
    private Integer status;
    private Integer changeState;
}
