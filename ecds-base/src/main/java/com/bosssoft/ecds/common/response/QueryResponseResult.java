package com.bosssoft.ecds.common.response;

/**
 * @author: lpb
 * @create: 2020-07-12 20:48
 * 统一的带有数据的查询响应结果
 */
public class QueryResponseResult<T> extends ResponseResult{

    public T query;

    public QueryResponseResult(ResultCode code,T query){
        super(code);
        this.query = query;
    }
}
