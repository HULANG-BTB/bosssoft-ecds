package com.bosssoft.ecds.exception;


import com.bosssoft.ecds.response.ResultCode;

/**
 * 自定义异常，用于在抛出异常时携带统一响应数据
 */
public class CustomException extends RuntimeException {

    private final ResultCode resultCode;

    private String errorMsg;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public CustomException(ResultCode resultCode, String errorMsg) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }


    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public int hashCode() {
        int result = resultCode != null ? resultCode.hashCode() : 0;
        return 31*result;
    }
}
