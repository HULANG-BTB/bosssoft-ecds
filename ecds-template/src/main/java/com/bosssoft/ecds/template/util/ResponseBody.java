package com.bosssoft.ecds.template.util;

import com.bosssoft.ecds.util.ResponseUtils;

/**
 * 统一响应类
 */
public class ResponseBody extends ResponseUtils.ResponseBody {

    public ResponseBody(){}

    public ResponseBody(Integer status, String msg, Object data) {
        setStatus(status);
        setMsg(msg);
        setData(data);
    }

    public ResponseBody(ResponseUtils.ResultType resultType, Object data){
        setStatus(resultType.getCode());
        setMsg(resultType.getMsg());
        setData(data);
    }

    public static ResponseBody ok(){
        return ok(null);
    }

    public static ResponseBody ok(Object data) {
        return new ResponseBody(ResponseUtils.ResultType.OK, data);
    }

    public static ResponseBody error() {
        return error(null);
    }

    public static ResponseBody error(String msg){
        ResponseBody responseBody = new ResponseBody();
        responseBody.setMsg(msg);
        responseBody.setStatus(ResponseUtils.ResultType.INTERNAL_SERVER_ERROR.getCode());
        return responseBody;
    }

}
