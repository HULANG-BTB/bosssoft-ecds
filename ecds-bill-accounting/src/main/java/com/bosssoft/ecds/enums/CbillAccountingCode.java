package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @ClassName CbillAccountingCode
 * @Description 入账数据异常码
 * @Auther UoweMe
 * @Date 2020/8/12 11:39
 * @Version 1.0
 */
public class CbillAccountingCode implements ResultCode {

    //自定义错误类型及返回码


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
