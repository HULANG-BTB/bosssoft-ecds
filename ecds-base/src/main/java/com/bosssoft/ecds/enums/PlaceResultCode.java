package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @author 吴志鸿
 * @date 2020/8/11
 * @description
 */
public enum  PlaceResultCode implements ResultCode {

    PLACE_NOT_EXISTS(false,6000,"开票点不存在");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    PlaceResultCode(boolean success,int code, String message){
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
