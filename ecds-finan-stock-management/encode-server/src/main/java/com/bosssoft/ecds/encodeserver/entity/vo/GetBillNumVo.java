package com.bosssoft.ecds.encodeserver.entity.vo;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author 黄杰峰
 * @Date 2020/8/17 0017 17:03
 * @Description
 */
@Data
public class GetBillNumVo implements Serializable {
    /**
     * 区划编码
     */
    @NotBlank(message = "请输入区划编码")
    @Alias("fRegiId")
    private String regiId;

    /**
     * 分类号
     */
    @NotBlank(message = "请输入分类编码")
    @Alias("fSortId")
    private String sortId;

    /**
     * 种类号
     */
    @NotBlank(message = "请输入种类编码")
    @Alias("fTypeId")
    private String typeId;

    /**
     * 年度号
     */
    @NotBlank(message = "请输入年度编码")
    @Alias("fAnnualId")
    private String annualId;

    /**
     * 申请的票数量，若为1则为申请单张票
     */
    @NotBlank(message = "请输入取号数量")
    @Alias("codeNum")
    private Integer codeNum;
}
