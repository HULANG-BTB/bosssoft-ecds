package com.bosssoft.ecds.common;

/**
 * @author: lpb
 * @create: 2020-07-10 09:02
 */
public enum CommonCode implements ResultCode {
    //
    SUCCESS(true, 10000L, "操作成功！"),
    FAIL(false, 11111L, "操作失败！"),
    INVLIDATE(false, 10003L, "非法参数！"),
    UNKNOWN_ERROR(false, 20001L, "未知错误"),
    PARAM_ERROR(false, 20002L, "参数错误"),
    PARAM_VALID_ERROR(false, 10004L, "参数验证错误"),
    METHOD_NOT_SUPPORTED(false, 1005L, "请求方法不受支持"),
    SERVER_ERROR(false, 99999L, "抱歉，系统繁忙，请稍后重试！"),
    DUPLICATE_ERROR(false, 21111L, "该数据已存在"),
    EMPTY(false, 99999L, "该数据为空！"),
    ENCRYPTION_ERROR(false, 21222L, "加密参数缺失"),
    EMAIL_NOTIFICATION(false, 29999L, "邮件通知"),
    PUBLIC_KEY_IS_NULL(false, 29229L, "未获取前端公钥"),
    PRIVATE_KEY_IS_NULL(false, 29729L, "后端私钥为空");
    /**
     * 操作是否成功
     */
    Boolean success;
    /**
     * 操作代码
     */
    Long code;
    /**
     * 提示信息
     */
    String message;

    CommonCode(Boolean success, Long code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public Boolean success() {
        return success;
    }

    @Override
    public Long code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
