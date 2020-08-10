package com.bosssoft.ecds.entity.vo.billtypevo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

import static com.bosssoft.ecds.constant.BillTypeConstant.ID_NOT_NULL;


/**
 * @author :Raiz
 * @date :2020/8/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBillTypeVO extends AddBillTypeVO {

    @NotNull(message = ID_NOT_NULL)
    Long id;
}
