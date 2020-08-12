package com.bosssoft.ecds.entity.vo.billtypevo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author :Raiz
 * @date :2020/8/6
 */
@Data
public class BillTypeIdVo {

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "ID_NOT_NULL")
    Long id;
}
