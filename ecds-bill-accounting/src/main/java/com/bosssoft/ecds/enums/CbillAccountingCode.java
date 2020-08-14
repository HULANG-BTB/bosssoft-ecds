package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @ClassName CbillAccountingCode
 * @Description 自定义的入账数据异常码
 * @Auther UoweMe
 * @Date 2020/8/12 11:39
 * @Version 1.0
 */
public enum  CbillAccountingCode implements ResultCode {

    //自定义错误类型及返回码
    SUCCESS(true, 1000, "操作成功！"),
    //操作失败时返回的状态码
    INSERT_FAIL(false,1001,"插入失败"),
    UPDATE_FAIL(false,1002,"更新失败"),
    DELETE_FAIL(false,1003,"删除失败"),
    BILL_SERIAL_ID_NOT_EXIST(false, 1004, "该票据校验码不存在"),
    PAYER_TEL_ILLEGAL(false,1005,"该缴款人电话不存在或不匹配"),
    ACCOUNT_ILLEGAL(false, 1006, "该票据校验码对应入账金额不正确"),
    ACCOUNT_TYPE_ILLEGAL(false,1007,"该入账类型不存在"),
    ACCOUNT_TIME_ILLEGAL(false,1008,"入账时间不合理"),
    BILL_NO_NOT_EXIST(false, 1009, "票据号码不存在"),
    BILL_NO_REPEAT(false, 1010, "票据号码冲突"),
    TIME_ILLEGAL(false, 1012, "开票时间不合理"),
    WAIT_ACCOUNT_ILLEGAL(false,1013,"待缴金额异常"),
    BILL_SERIAL_ID_REPEAT(false,1014,"票据校验码冲突"),
    FINISHED(true,1015,"待缴金额已入账"),
    UNFINISHED(false,1016,"待缴金额暂未入账"),
    VOUCHER_FAIL(false,1017,"生成凭证失败")
            ;

    boolean success;

    int code;

    String message;

    CbillAccountingCode(boolean success,int code,String message){
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
