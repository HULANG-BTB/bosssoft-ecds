package com.bosssoft.ecds.entity.vo;

import lombok.Data;

/**
 * 类功能描述：审核VO
 * @author 朱文
 * create on 2020/8/22 17:09
 */
@Data
public class StockReturnCheckVO {

    /**业务单号**/
    private Long no;

    /**审核人**/
    private String changeMan;

    /**审核意见**/
    private String changeSitu;

    /**审核状态**/
    private Integer changeState;
}
