package com.bosssoft.ecds.template.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MergeResult {
    private Boolean isMerge;
    private int startRow;
    private int endRow;
    private int startColumn;
    private int endColumn;
}
