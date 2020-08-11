package com.bosssoft.ecds.common.response;

import com.bosssoft.ecds.utils.DynamicEnumUtil;

/**
 * @author: lpb
 * @create: 2020-07-10 09:02
 */
public enum CommonCode implements ResultCode {

    //操作成功时返回的状态码
    SUCCESS(true,10000,"操作成功！"),
    //操作失败时返回的状态码
    FAIL(false,11111,"操作失败！"),
    //参数输入有误时返回的状态码
    INVLIDATE(false,10003,"非法参数！"),

    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
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

    /**
     * 动态创建枚举类方法
     * @param enumName 枚举名称
     * @param success 是否成功
     * @param code 枚举编码
     * @param message 枚举消息
     * @return
     */
    public static CommonCode addEnum(String enumName, boolean success, int code, String message){
        CommonCode createCode = DynamicEnumUtil.addEnum(CommonCode.class, enumName, new Class[]{Boolean.class, Integer.class,String.class}, new Object[]{success,code, message});
        return createCode;
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
