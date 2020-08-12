package com.bosssoft.ecds.encodeserver.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 黄杰峰
 * @Date 2020/8/7 0007 15:36
 * @Description
 */
@Data
public class RestResult implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public RestResult() {
    }

    public RestResult(ResultCode resultCode, Object data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public static RestResult success() {
        RestResult result = new RestResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static RestResult success(Object object) {
        return new RestResult(ResultCode.SUCCESS, object);
    }

    public static RestResult failure() {
        RestResult result = new RestResult();
        result.setResultCode(ResultCode.FAILURE);
        return result;
    }

    public static RestResult failure(String failureMsg) {
        return new RestResult(ResultCode.FAILURE, failureMsg);
    }

    public static RestResult isSuccess(boolean success) {
        if (success) {
            return success();
        } else {
            return failure();
        }
    }
}

enum ResultCode {
    // 赋码成功
    SUCCESS(200, "成功"),
    // 赋码失败
    FAILURE(500, "失败");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
