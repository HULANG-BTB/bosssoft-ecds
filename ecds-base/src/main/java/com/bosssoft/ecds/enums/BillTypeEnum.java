package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @author :Raiz
 * @date :2020/8/5
 */
public enum BillTypeEnum implements ResultCode {

    SUCCESS(true, 10000, "操作成功！"),
    //操作失败时返回的状态码
    FAIL(false, 11111, "操作失败！"),
    ADD_BILL_TYPE_FAIL(false, 20001, "添加票据种类失败"),
    UPDATE_BILL_TYPE_FAIL(false, 20001, "更新票据种类信息失败"),
    BILL_TYPE_NAME_EXIST(false, 20002, "票据种类名称或者票据编码已存在"),
    BILL_TYPE_DONT_EXIST(false, 20003, "此id票据种类不存在"),
    BILL_CODE_ILLEGAL(false, 20004, "子票据种类编码要以父类编码为前缀"),
    BILL_TYPE_CHILD_EXIST(false, 20005, "此票据分类下仍用票据种类"),
    DELETE_BILL_TYPE_FAIL(false, 20006, "删除票据分类失败"),
    BILL_TYPE_ILLEGAL(false, 20006, "只能票据分类做为上级"),
    ;

    //操作是否成功
    boolean success;
    //操作代码
    int code;

    BillTypeEnum(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    //提示信息
    String message;

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
