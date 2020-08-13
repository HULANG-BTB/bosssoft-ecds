package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @author: lpb
 * @create: 2020-08-10 19:47
 */
public enum RegionCode implements ResultCode {

    REGION_NAME_EXISTS(false,20001,"区划名称或编码已存在！"),
    REGION_NOTEXISTS(false,20002,"区划信息不存在或已被删除，请刷新后重试！"),
    CATEGORY_HASSON(false,20003,"当前节点含有子节点，无法删除！"),
    ;

    boolean success;

    int code;

    String message;

    RegionCode(boolean success,int code, String message){
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
