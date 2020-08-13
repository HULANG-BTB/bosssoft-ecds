package com.bosssoft.ecds.encodeserver.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author 黄杰峰
 * @Date 2020/8/6 0006 17:37
 * @Description
 */
@Data
public class GetFinanceNumDto implements Serializable {
    /**
     * 区划编码
     */
    @NotBlank(message = "请输入区划编码")
    private String fRegiId;

    /**
     * 分类号
     */
    @NotBlank(message = "请输入分类编码")
    private String fSortId;

    /**
     * 种类号
     */
    @NotBlank(message = "请输入种类编码")
    private String fTypeId;

    /**
     * 年度号
     */
    @NotBlank(message = "请输入年度编码")
    private String fAnnualId;

    /**
     * 申请的票数量，若为1则为申请单张票
     */
    @NotBlank(message = "请输入取号数量")
    private Integer codeNum;

    /**
     * 获取8位财政编码
     * @return
     */
    public String financeCode() {
        return fRegiId + fSortId + fTypeId + fAnnualId;
    }
}
