package com.bosssoft.ecds.exception;


import com.bosssoft.ecds.response.ResultCode;

/**
 * 用于抛出自定义的指定状态码的异常信息
 */
public class ExceptionCast  {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }

}
