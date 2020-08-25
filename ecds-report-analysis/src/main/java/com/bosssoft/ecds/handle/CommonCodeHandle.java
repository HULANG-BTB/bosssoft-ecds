package com.bosssoft.ecds.handle;

/**
 * @ClassName CommonCodeHandle
 * @Description TODO
 * @auther wangpeng
 * @Date 2020/8/23 17:21
 * @Version 1.0
 **/


import com.bosssoft.ecds.response.ResultCode;


/**
 * @author: lpb
 * @create: 2020-07-10 09:02
 */
public enum CommonCodeHandle implements ResultCode {

    //时间条件查询成功时返回的状态码
    Time_SUCCESS(true, 10111, "缴费明细通过时间查询成功！"),
    //时间条件查询失败时返回的状态码
    Time_FAIL(false, 10112, "缴费明细通过时间查询失败！"),
    //多条件查询成功时返回的状态码
    ALL_SUCCESS(true, 10113, "缴费明细多条件查询成功！"),
    //多条件查询失败时返回的状态码
    ALL_FAIL(false, 10114, "缴费明细多条件查询失败！"),
    //多条件查询成功时返回的状态码
    WEEK_SUCCESS(true, 10115, "近七天缴费查询成功！"),
    //多条件查询失败时返回的状态码
    WEEK_FAIL(false, 10116, "近七天缴费查询失败！");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CommonCodeHandle(boolean success, int code, String message) {
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

