package com.bosssoft.ecds.template.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 作为填充内容，用来向图片中插入文字
 */
@Data
@AllArgsConstructor
public class TextValue {
    /** 填充内容的字段名 */
    private String fieldName;

    /** 填充内容 */
    private String value;

    /** x轴坐标 */
    private int x;

    /** y轴坐标 */
    private int y;

    public TextValue(String fieldName, int x, int y){
        this.fieldName = fieldName;
        this.x = x;
        this.y = y;
    }
}
