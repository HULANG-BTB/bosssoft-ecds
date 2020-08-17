package com.bosssoft.ecds.common.response;

/**
 * @ClassName QueryResponseResult
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/12 11:36
 * @Version 1.0
 */
public class QueryResponseResult<T> extends ResponseResult {

    public T data;

    public QueryResponseResult(ResultCode code, T data){
        super(code);
        this.data = data;
    }
}
