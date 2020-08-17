package com.bosssoft.ecds.encodeserver.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @Author 黄杰峰
 * @Date 2020/8/7 0007 10:34
 * @Description 创建财政编码DTO，需要加入创建人名称和创建人id。
 */
@Data
public class CreateFinanceCodeDto {
    /**
     * 区划编码
     */
    private String fRegiId;

    /**
     * 分类号
     */
    private String fSortId;

    /**
     * 种类号
     */
    private String fTypeId;

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

    public String financeCode() {
        return fRegiId + fSortId + fTypeId + fAnnualId;
    }
}
