package com.bosssoft.ecds.common.exception;

import com.bosssoft.ecds.common.ResultCode;

/**
 * 用于抛出自定义的指定状态码的异常信息
 *
 * @author liuke
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }

}
