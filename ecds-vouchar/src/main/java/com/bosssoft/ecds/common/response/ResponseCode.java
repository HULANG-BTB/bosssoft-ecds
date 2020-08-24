package com.bosssoft.ecds.common.response;

import com.bosssoft.ecds.response.ResultCode;

/**
 * @author liuke
 * @apiNote 返回结果代码
 */
public class ResponseCode implements ResultCode {

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
