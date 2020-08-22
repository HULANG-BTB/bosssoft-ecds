package com.bosssoft.ecds.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author :Raiz
 * @date :2020/8/6
 */
@Data
public class BillSortDTO {

    @JsonSerialize(using = ToStringSerializer.class)
    Long id;

    String code;

    String name;
}
