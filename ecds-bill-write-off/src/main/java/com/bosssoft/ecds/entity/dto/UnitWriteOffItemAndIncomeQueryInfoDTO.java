package com.bosssoft.ecds.entity.dto;

import lombok.Data;

/**
 * @author hujierong
 * @date 2020-8-17
 */
@Data
public class UnitWriteOffItemAndIncomeQueryInfoDTO {
    /**
     * 业务单号
     */
    private String no;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页的数据数量
     */
    private int pageSize;
}
