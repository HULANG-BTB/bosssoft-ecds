package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Data
public class UnitWriteOffApplyQueryInfoVO {
    /**
     * 业务单号
     */
    private String no;

    /**
     * 日期范围
     */
    private Date[] rangeDate;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页的数据数量
     */
    private int pageSize;
}
