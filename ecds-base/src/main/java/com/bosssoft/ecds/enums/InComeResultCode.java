package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/17 9:20
 */
public enum InComeResultCode implements ResultCode {

    INCOME_NAME_EXISTS(false, 2001, "收入类别名已存在"),
    INCOME_CODE_FORM_ERROR(false, 2002, "收入类别编码格式异常"),
    INCOME_CODE_NUM_ERROR(false, 2003, "收入类别编码数据异常"),
    INCOME_CODE_EXISTS(false, 2004, "收入类别编码已存在"),
    INCOME_NAME_NOT_EXISTS(false, 2005, "收入类别不存在"),
    ITEM_EXISTS(false, 2006, "项目中存在收入类别"),
    SUBJECT_EXISTS(false, 2006, "项目中存在收入类别");

    /**
     * 操作是否成功
     */
    boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;


    InComeResultCode(boolean success, int code, String message) {
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
