package com.bosssoft.ecds.common.exception;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @author liuke
 * @apiNote 异常处理代码
 */
public enum MyExceptionCode implements ResultCode {
    // 数据不存在
    BILL_APPLY_DATE_EMPTY(false, 70000, "票据申请数据为空"),
    BILL_AVAILABLE_DATE_EMPTY(false, 70001, "可用票据数据为空"),
    ITEM_AVAILABLE_DATE_EMPTY(false, 70002, "可用项目是数据为空"),
    BILL_CHECK_DATE_EMPTY(false, 70003, "票据审验数据为空"),
    BILL_PAY_DATE_EMPTY(false, 70004, "票据缴款数据为空"),
    BILL_WARN_DATE_EMPTY(false, 70005, "票据预警数据为空");

    /**
     * 操作是否成功
     */
    boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;

    MyExceptionCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
