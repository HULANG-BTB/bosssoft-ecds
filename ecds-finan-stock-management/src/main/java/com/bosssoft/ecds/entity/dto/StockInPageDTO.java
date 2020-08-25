package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 封装分页查询参数的DTO
 *
 * @author cheng
 * @Date 2020/8/15 19:27
 */
@Data
public class StockInPageDTO {
    /**
     * 业务号
     */
    private Long no;
    
    /**
     * 审核状态
     */
    private Integer changeState;
    
    /**
     * 时间范围开始
     */
    private Date start;
    
    /**
     * 时间范围结束
     */
    private Date end;
    
    /**
     * 每页条目数
     */
    private Integer limit;
    
    /**
     * 请求的页码号
     */
    private Long page;
    
    /**
     * 总记录数
     */
    private Long total;
    
    /**
     * 入库状态
     */
    private Integer status;
}
