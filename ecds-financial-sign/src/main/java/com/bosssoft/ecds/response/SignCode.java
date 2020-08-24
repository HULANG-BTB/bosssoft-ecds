package com.bosssoft.ecds.response;

/**
 * 签名服务器自定义异常状态码
 */
public enum SignCode implements ResultCode{

    /**
     * 盖章成功
     */
    STAMP_SUCCESS(true,10000,"成功盖章"),
    /**
     * 盖章失败，验证签名出现错误
     */
    STAMP_FAILURE(false,11111,"盖章失败，签名过期或签名错误"),
    /**
     * 公钥证书无法解密签名，签名证书不一致
     */
    WRONG_CERT(false,11180,"证书与签名不对应"),
    /**
     * 证书存在异常，公钥证书crt与其CA签名不一致，证书非法或被篡改
     */
    WRONG_CERT_SIGN(false,11181,"证书与CA签名不符，证书非法"),
    /**
     * 签名编码方式不一致
     */
    WRONG_DECODE(false,11182,"解码失败，请确认编码形式"),
    /**
     * 签名算法非SH256,暂不支持
     */
    WRONG_ALGO(false,11183,"暂不支持此种算法"),
    /**
     * 读取证书失败
     */
    WRONG_PROVIDER(false,11184,"证书读取失败，证书非法或失效"),
    /**
     * 证书失效或非法
     */
    CERT_E(false,11185,"证书非法或失效");
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
