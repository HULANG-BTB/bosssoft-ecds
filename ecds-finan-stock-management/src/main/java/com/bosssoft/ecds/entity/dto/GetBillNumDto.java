package com.bosssoft.ecds.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author 黄杰峰
 * @Date 2020/8/6 0006 17:37
 * @Description
 */
@Data
public class GetBillNumDto implements Serializable {
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
     * 申请的票数量，若为1则为申请单张票
     */
    private Integer codeNum;

    /**
     * 获取8位财政编码
     * @return
     */
    public String financeCode() {
        return fRegiId + fSortId + fTypeId + fAnnualId;
    }
}
