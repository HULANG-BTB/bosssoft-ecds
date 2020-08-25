package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @description: FabFinDeptResult异常信息
 * @author: lin.wanning
 * @date: Created in 2020/8/10 16:41
 * @modified By:
 */
public enum FabFinDeptResultCode implements ResultCode {

    CATEGORY_HASSON(false, 2200, "当前部门存在挂靠，无法删除！");


    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    FabFinDeptResultCode(boolean success, int code, String message) {
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
