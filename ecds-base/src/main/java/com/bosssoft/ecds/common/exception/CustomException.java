package com.bosssoft.ecds.common.exception;


import com.bosssoft.ecds.common.response.ResultCode;

public class CustomException extends RuntimeException {

    private ResultCode resultCode;
    private  Class clazz;
    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
        this.clazz=CustomException.class;
    }
    public CustomException(ResultCode resultCode,Class clazz){
        this.resultCode = resultCode;
        this.clazz=clazz;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    @Override
    public int hashCode() {
        int result = resultCode != null ? resultCode.hashCode() : 0;
        return 31*result;
    }
}
