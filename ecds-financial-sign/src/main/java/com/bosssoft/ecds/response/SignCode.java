package com.bosssoft.ecds.response;

/**
 * 签名服务器自定义异常状态码
 */
public enum SignCode implements ResultCode{

    WRONG_CERT(false,11180,"证书与签名不对应"),
    WRONG_CERT_SIGN(false,11181,"证书与CA签名不符，证书非法"),
    WRONG_DECODE(false,11182,"解码失败，请确认编码形式"),
    WRONG_ALGO(false,11183,"暂不支持此种算法"),
    WRONG_PROVIDER(false,11184,"证书读取失败，证书非法或失效"),
    CERT_E(false,11185,"证书非法或失效"),
    ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    SignCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
