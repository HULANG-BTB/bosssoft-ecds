package com.bosssoft.ecds.response;

/**
 * @author: lpb
 * @create: 2020-07-12 20:48
 * 统一的带有数据的查询响应结果
 */
public class QueryResponseResult<T> extends ResponseResult {

    public T data;

    public String aseKey;

    public QueryResponseResult(ResultCode code, T data) {
        super(code);
        this.data = data;
    }

    public QueryResponseResult(ResultCode code, T data, String aseKey) {
        super(code);
        this.data = data;
        this.aseKey = aseKey;
    }
}
