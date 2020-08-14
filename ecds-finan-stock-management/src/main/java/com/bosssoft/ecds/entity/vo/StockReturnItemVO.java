package com.bosssoft.ecds.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 退票明细VO
 * @author 朱文
 * create on 2020/8/11 14:37
 */

@Data
@ToString
public class StockReturnItemVO {

    //序号
    private Long no;

    //票据代码
    private String billCode;

    //数量
    private Integer number;

    //起始
    private String billNo1;

    //终止
    private String billNo2;
}
