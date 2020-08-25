package com.bosssoft.ecds.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName FneCbillAccountingWeekVO
 * @Description 近七天每天入账总金额
 * @auther wangpeng
 * @Date 2020/8/23 23:44
 * @Version 1.0
 **/
@Data
public class FneCbillAccountingWeekVO {
    /**
     * "入账总金额"
     **/
    private List<BigDecimal> fAccountSumList;
}
