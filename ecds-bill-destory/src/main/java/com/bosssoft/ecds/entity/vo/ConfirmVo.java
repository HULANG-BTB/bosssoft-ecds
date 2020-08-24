package com.bosssoft.ecds.entity.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: qiuheng
 * @create: 2020-08-21 10:08
 **/
public class ConfirmVo implements Serializable {
    /*
     *审核时间
     */
//    private LocalDateTime fConfirmTime;

    /*
     *审核结果
     */
    private boolean fResult;

    /*
     *审核备注
     */
    private String fConfirmMemo;

    /*
     *票据销毁申请单号
     */
    private String fDestroyNo;

    /*
     *审核人
     */
    private String fConfirmMan;

//    public LocalDateTime getfConfirmTime() {
//        return fConfirmTime;
//    }
//
//    public void setfConfirmTime(LocalDateTime fConfirmTime) {
//        this.fConfirmTime = fConfirmTime;
//    }

    public boolean isfResult() {
        return fResult;
    }

    public void setfResult(boolean fResult) {
        this.fResult = fResult;
    }

    public String getfConfirmMemo() {
        return fConfirmMemo;
    }

    public void setfConfirmMemo(String fConfirmMemo) {
        this.fConfirmMemo = fConfirmMemo;
    }

    public String getfDestroyNo() {
        return fDestroyNo;
    }

    public void setfDestroyNo(String fDestroyNo) {
        this.fDestroyNo = fDestroyNo;
    }

    public String getfConfirmMan() {
        return fConfirmMan;
    }

    public void setfConfirmMan(String fConfirmMan) {
        this.fConfirmMan = fConfirmMan;
    }
}
