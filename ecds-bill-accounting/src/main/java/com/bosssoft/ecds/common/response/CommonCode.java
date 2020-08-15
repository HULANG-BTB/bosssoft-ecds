package com.bosssoft.ecds.common.response;

/**
 * @ClassName CommonCode
 * @Description 通用返回码
 * @Auther UoweMe
 * @Date 2020/8/12 11:31
 * @Version 1.0
 */
public enum CommonCode implements ResultCode {

    //操作成功时返回的状态码
    SUCCESS(true,1000,"操作成功！"),
    //操作失败时返回的状态码
    FAIL(false,999,"操作失败！"),
    //参数输入有误时返回的状态码
    INVALIDATE(false,998,"非法参数！"),
    METHOD_NOT_SUPPORTED(false,997, "请求方法不受支持"),

    SERVER_ERROR(false,9999,"抱歉，系统繁忙，请稍后重试！"),
    ;

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CommonCode(boolean success,int code, String message){
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
