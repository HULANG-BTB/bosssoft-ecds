package com.bosssoft.ecds.template.entity;

import lombok.Data;

@Data
public class XMLItem {
    //记录是不是需要从数据库存数据
    private String isData;
    private String isNewRow;
    private Integer colspan;
    private Integer rowspan;
    private String alignment;
    private String verticalAlignment;
    private String fontName;
    private Integer fontHeight;
    private String fontColor;
    private String content;
    private String parent;
    private String prefix;
}
