package com.bosssoft.ecds.entity.vo.billtypevo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :Raiz
 * @date :2020/8/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTableVO extends PageVO {

    Long id;

    String billNature;

    String name;

    String memoryCode;

    Integer checkSort;
}
