package com.bosssoft.ecds.enums;


import com.bosssoft.ecds.response.ResultCode;

/**
 * @ClassName CbillAccountingCode
 * @Description 自定义的入账凭证数据异常码
 * @Auther UoweMe
 * @Date 2020/8/12 11:39
 * @Version 1.0
 */
public enum VoucherCode implements ResultCode {

    //自定义错误类型及返回码
    //自定义错误类型及返回码
    SUCCESS(true, 10000, "操作成功！"),
    //操作失败时返回的状态码
    INSERT_FAIL(false,30001,"插入失败"),
    UPDATE_FAIL(false,30002,"更新失败"),
    DELETE_FAIL(false,30003,"删除失败"),
    ACCOUNT_ID_NOT_EXIST(false, 30004, "该电子凭证号码不存在"),
    ;
    boolean success;

    int code;

    String message;

    VoucherCode(boolean success,int code,String message){
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
