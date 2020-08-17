package com.bosssoft.ecds.utils.response;

/**
 * @author: lpb
 * @create: 2020-07-10 09:02
 * * 10000-- 通用错误代码
 * * 22000-- 用户错误代码
 */
public interface ResultCode {

    /**
     * 操作是否成功,true为成功，false操作失败
     *
     * @return boolean
     */
    boolean success();

    /**
     * 操作代码
     *
     * @return int
     */
    int code();

    /**
     * 提示信息
     *
     * @return
     */
    String message();

}

