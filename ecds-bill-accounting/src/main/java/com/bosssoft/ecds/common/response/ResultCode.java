package com.bosssoft.ecds.common.response;

/**
 * @InterfaceName ResultCode
 * @Description 结果返回码
 * @Auther UoweMe
 * @Date 2020/8/12 11:32
 * @Version 1.0
 */
public interface ResultCode {

    //操作是否成功,true为成功，false操作失败
    boolean success();
    //操作代码
    int code();
    //提示信息
    String message();
}
