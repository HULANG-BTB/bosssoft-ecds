package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @author 吴志鸿
 * @date 2020/8/10
 * @description
 */
public enum  ItemResultCode implements ResultCode {

    ITEM_NOT_EXISTS(false,5000,"项目不存在"),
    ITEM_STD_NOT_EXISTS(false,5001,"项目标准不存在"),
    ITEM_STD_EXISTS(false,5003,"项目标准已经存在，无法添加"),
    NOT_EXISTS(false,5002,"删除失败，没有此条记录");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ItemResultCode(boolean success,int code, String message){
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
