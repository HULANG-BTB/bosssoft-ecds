package com.bosssoft.ecds.code;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @author: lpb
 * @create: 2020-08-18 15:59
 */
public enum  GatewayCode implements ResultCode {

    TOO_MANY_REQUESTS(false,11001,"当前请求过多，请稍后再试"),
    BAD_REQUEST(false,401001,"错误请求"),
    NOT_FOUND_SERVICE(false,401002,"找不到存在的实例"),
    REQUEST_REMOTE_FAIL(false,401002,"远程调用失败"),
    ;

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    GatewayCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
