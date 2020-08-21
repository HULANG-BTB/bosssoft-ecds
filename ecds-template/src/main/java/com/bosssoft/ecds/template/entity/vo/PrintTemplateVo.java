package com.bosssoft.ecds.template.entity.vo;

import lombok.Data;

@Data
public class PrintTemplateVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 区划编码
     */
    private String rgnCode;

    /**
     * 种类编码
     */
    private String sortId;

    /**
     * 分类编码
     */
    private String typeId;

    /**
     * 单位识别码
     */
    private String agenIdCode;

    /**
     * 开票点ID
     */
    private String placeId;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 备注
     */
    private String memo;
}
