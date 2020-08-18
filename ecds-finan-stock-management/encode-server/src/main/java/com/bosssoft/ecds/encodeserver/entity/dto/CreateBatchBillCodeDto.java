package com.bosssoft.ecds.encodeserver.entity.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author 黄杰峰
 * @Date 2020/8/18 0018 9:28
 * @Description 批量获取票据号码Dto
 */
@Data
public class CreateBatchBillCodeDto {
    /**
     * 区划编码
     */
    private String fRegiId;

    /**
     * 分类号
     */
    private String fSortId;

    /**
     * 种类号起始
     */
    private String fTypeStartId;

    /**
     * 种类号终止Id
     */
    private String fTypeEndId;

    /**
     * 年度号
     */
    private String fAnnualId;

    /**
     * 操作人名称
     */
    private String fOperator;

    /**
     * 操作人ID
     */
    private Long fOperatorId;

    /**
     * 码创建时间
     */
    private Timestamp fCreateTime;

    /**
     * 码更新时间
     */
    private Timestamp fUpdateTime;
}
