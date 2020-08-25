package com.bosssoft.ecds.enums;


import com.bosssoft.ecds.response.ResultCode;

/**
 * @ClassName CbillAccountingCode
 * @Description 自定义的入账数据异常码
 * @Auther UoweMe
 * @Date 2020/8/12 11:39
 * @Version 1.0
 */
public enum  CbillAccountingCode implements ResultCode {

    //自定义错误类型及返回码
    SUCCESS(true, 10000, "操作成功！"),
    //操作失败时返回的状态码
    INSERT_FAIL(false,30001,"插入失败"),
    UPDATE_FAIL(false,30002,"更新失败"),
    DELETE_FAIL(false,30003,"删除失败"),
    BILL_SERIAL_ID_NOT_EXIST(false, 30004, "该票据校验码不存在"),
    PAYER_TEL_ILLEGAL(false,30005,"该缴款人电话不存在或不匹配"),
    ACCOUNT_ILLEGAL(false, 30006, "该票据校验码对应入账金额不正确"),
    ACCOUNT_TIME_ILLEGAL(false,30007,"入账时间不合理"),
    BILL_NO_NOT_EXIST(false, 30008, "票据号码不存在"),
    FINISHED(true,30009,"待缴金额已入账"),
    UNFINISHED(true,30010,"待缴金额暂未入账"),
    BILL_NO_REPEAT(false, 30011, "票据号码冲突"),
    TIME_ILLEGAL(false, 30012, "开票时间不合理"),
    BILL_SERIAL_ID_REPEAT(false,30013,"票据校验码冲突"),
    VOUCHER_FAIL(false,30014,"生成凭证失败"),
    AGEN_IDCODE_NOT_EXIST(true,30015,"不存在该单位代码")
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
