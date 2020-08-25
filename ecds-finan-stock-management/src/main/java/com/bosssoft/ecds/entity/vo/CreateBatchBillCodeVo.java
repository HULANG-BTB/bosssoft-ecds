package com.bosssoft.ecds.entity.vo;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author 黄杰峰
 * @Date 2020/8/18 0018 10:43
 * @Description
 */
@Data
public class CreateBatchBillCodeVo implements Serializable {
    /**
     * 区划编码
     */
    @NotBlank(message = "区划Id不能为空")
    @Alias("fRegiId")
    private String regiId;

    /**
     * 分类号
     */
    @NotBlank(message = "分类编码不能为空")
    @Alias("fSortId")
    private String sortId;

    /**
     * 种类号起始
     */
    @NotBlank(message = "起始种类码不能为空")
    @Alias("fTypeStartId")
    private String typeStartId;

    /**
     * 种类号终止Id
     */
    @NotBlank(message = "终止种类码不能为空")
    @Alias("fTypeEndId")
    private String typeEndId;

    /**
     * 年度号
     */
    @NotBlank(message = "年度编码不能为空")
    @Alias("fAnnualId")
    private String annualId;

    /**
     * 操作人名称
     */
    @NotBlank(message = "操作人姓名不能为空")
    @Alias("fOperator")
    private String operator;

    /**
     * 操作人ID
     */
    @NotBlank(message = "操作人Id不能为空")
    @Alias("fOperatorId")
    private Long operatorId;

}
