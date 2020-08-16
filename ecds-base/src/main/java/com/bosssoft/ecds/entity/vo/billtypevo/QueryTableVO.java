package com.bosssoft.ecds.entity.vo.billtypevo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :Raiz
 * @date :2020/8/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTableVO extends PageVO {

    @JsonSerialize(using = ToStringSerializer.class)
    Long id;

    String billNature;

    String name;

    String memoryCode;

    Integer checkSort;
}
