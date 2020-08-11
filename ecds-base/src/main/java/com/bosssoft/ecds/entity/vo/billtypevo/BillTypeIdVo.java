package com.bosssoft.ecds.entity.vo.billtypevo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author :Raiz
 * @date :2020/8/6
 */
@Data
public class BillTypeIdVo {
    @NotNull(message = "ID_NOT_NULL")
    Long id;
}
