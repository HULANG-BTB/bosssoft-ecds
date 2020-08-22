package com.bosssoft.ecds.common.exception;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @author liuke
 */
public enum MyExceptionCode implements ResultCode {
    // 数据不存在
    DATE_EMPTY(false, 4444444, "该数据不存在"),
    ;

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
