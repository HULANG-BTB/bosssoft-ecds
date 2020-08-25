package com.bosssoft.ecds.entity.vo;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-19
 */
public class BillInfoVO {
    /**
     * 票号信息列表
     */
    private List<WriteOffApplyItemVO> applyItemVOS;

    /**
     * 项目情况列表
     */
    private List<WriteOffApplyIncomeVO> applyIncomeVOS;

    public List<WriteOffApplyItemVO> getApplyItemVOS() {
        return applyItemVOS;
    }

    public void setApplyItemVOS(List<WriteOffApplyItemVO> applyItemVOS) {
        this.applyItemVOS = applyItemVOS;
    }

    public List<WriteOffApplyIncomeVO> getApplyIncomeVOS() {
        return applyIncomeVOS;
    }

    public void setApplyIncomeVOS(List<WriteOffApplyIncomeVO> applyIncomeVOS) {
        this.applyIncomeVOS = applyIncomeVOS;
    }
}
