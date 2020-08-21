package com.bosssoft.ecds.entity.vo;

/**
 * @author: qiuheng
 * @create: 2020-08-21 14:05
 **/
public class ResultVo {
    /**
     * 单号
     */
    private Long fDestroyNo;

    /**
     * 审核状态：0.未审核；1.已审核但未通过；2.已审核并通过
     */
    private String fStatus;

    public Long getfDestroyNo() {
        return fDestroyNo;
    }

    public void setfDestroyNo(Long fDestroyNo) {
        this.fDestroyNo = fDestroyNo;
    }

    public String getfStatus() {
        return fStatus;
    }

    public void setfStatus(String fStatus) {
        this.fStatus = fStatus;
    }
}
