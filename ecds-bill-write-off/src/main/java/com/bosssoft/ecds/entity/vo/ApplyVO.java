package com.bosssoft.ecds.entity.vo;

/**
 * @author hujierong
 * @date 2020-8-20
 */
public class ApplyVO {
    private WriteOffApplyVO apply;

    private BillInfoVO billInfo;

    public WriteOffApplyVO getApply() {
        return apply;
    }

    public void setApply(WriteOffApplyVO apply) {
        this.apply = apply;
    }

    public BillInfoVO getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(BillInfoVO billInfo) {
        this.billInfo = billInfo;
    }
}
