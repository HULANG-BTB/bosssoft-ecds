package com.bosssoft.ecds.common;

/**
 * @author liuke
 */
public interface ResultCode {
    /**
     * 操作是否成功
     */
    Boolean success();

    /**
     * 操作结果代码
     */
    Long code();

    /**
     * 提示信息
     */
    String message();
}
