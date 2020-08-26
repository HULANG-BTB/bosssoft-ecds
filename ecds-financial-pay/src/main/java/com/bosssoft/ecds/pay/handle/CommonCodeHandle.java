package com.bosssoft.ecds.pay.handle;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @ClassName CommonCodeUtil
 * @Description 自定义响应码
 * @auther wangpeng
 * @Date 2020/8/20 14:27
 * @Version 1.0
 **/
public enum CommonCodeHandle implements ResultCode {

    LOGIN_SUCCESS(true,111110,"缴费系统登录成功"),

    LOGIN_FAIL(false,111120,"缴费系统登录失败"),

    QrCode_SUCCESS(true,111130,"获取二维码成功"),

    QrCode_FAIL(false,111140,"获取二维码失败"),

    PAY_SUCCESS(true,111150,"支付成功"),

    PAY_FAIL(false,111160,"支付失败请重试"),

    GET_SUCCESS(true,111170,"Yes"),

    GET_FAIL(false,111180,"NO"),

    Data_FAILED(false,111200,"服务不在线"),

    Date_FAILED(false,111201,"支付不在线");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CommonCodeHandle(boolean success, int code, String message) {
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

