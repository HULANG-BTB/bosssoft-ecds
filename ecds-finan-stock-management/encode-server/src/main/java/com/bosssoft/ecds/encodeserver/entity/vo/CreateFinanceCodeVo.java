package com.bosssoft.ecds.encodeserver.entity.vo;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @Author 黄杰峰
 * @Date 2020/8/17 0017 17:03
 * @Description
 */
@Data
public class CreateFinanceCodeVo {
    /**
     * 区划编码
     */
    @NotBlank
    @Alias("fRegiId")
    private String regiId;

    /**
     * 分类号
     */
    @NotBlank
    @Alias("fSortId")
    private String sortId;

    /**
     * 种类号
     */
    @NotBlank
    @Alias("fTypeId")
    private String typeId;

    /**
     * 年度号
     */
    @NotBlank
    @Alias("fAnnualId")
    private String annualId;

    /**
     * 操作人名称
     */
    @NotBlank
    @Alias("fOperator")
    private String operator;

    /**
     * 操作人ID
     */
    @NotBlank
    @Alias("fOperatorId")
    private Long operatorId;
}
