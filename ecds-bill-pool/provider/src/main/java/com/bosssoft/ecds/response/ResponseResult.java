package com.bosssoft.ecds.response;

/**
 * @author: lpb
 * @create: 2020-07-10 09:29
 * 统一的响应结果
 */
public class ResponseResult {

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    public ResponseResult(ResultCode resultCode) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public ResponseResult(ResultCode resultCode, String message) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        if (message != null && !"".equals(message.trim())) {
            this.message = message;
        } else {
            this.message = resultCode.message();
        }
    }

    public ResponseResult(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL() {
        return new ResponseResult(CommonCode.FAIL);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
