package com.bosssoft.ecds.common.exception;

import com.bosssoft.ecds.common.ResultCode;

/**
 * @author liuke
 */
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    @Override
    public int hashCode() {
        int result = resultCode != null ? resultCode.hashCode() : 0;
        return 31 * result;
    }
}
