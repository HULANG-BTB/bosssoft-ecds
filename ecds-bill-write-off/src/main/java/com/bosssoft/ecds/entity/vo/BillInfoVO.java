package com.bosssoft.ecds.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-19
 */
@Data
public class BillInfoVO {
    /**
     * 票号信息列表
     */
    private List<WriteOffApplyItemVO> applyItemVOS;

    /**
     * 项目情况列表
     */
    private List<WriteOffApplyIncomeVO> applyIncomeVOS;
}
