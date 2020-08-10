package com.bosssoft.ecds.entity.vo.billtypevo;

import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.bosssoft.ecds.constant.BillTypeConstant.PAGE_NUM_NOT_NULL;
import static com.bosssoft.ecds.constant.BillTypeConstant.PAGE_SIZE_NOT_NULL;

/**
 * @author :Raiz
 * @date :2020/8/7
 */
@Data
public class PageVO {

    @NotNull(message = PAGE_NUM_NOT_NULL)
    Integer pageNum;

    @NotNull(message = PAGE_SIZE_NOT_NULL)
    Integer pageSize;
}
