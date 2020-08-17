package com.bosssoft.ecds.common.response;

import java.util.List;

/**
 * @ClassName QueryResult
 * @Description  分页查询时使用到的数据类型
 * list存储分页的数据，total存储数据总条数
 * @Auther UoweMe
 * @Date 2020/8/12 11:37
 * @Version 1.0
 */
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
